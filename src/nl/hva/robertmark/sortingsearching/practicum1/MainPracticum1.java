package nl.hva.robertmark.sortingsearching.practicum1;

import nl.hva.dmci.ict.inf.ads.lib.StdRandom;

import java.util.List;

/**
 * Created by Robert on 27-11-16.
 */
public class MainPracticum1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        MainPracticum1 main = new MainPracticum1();
        main.run();

    }

    public void run() {

        float[] testGrades = {10f, 9f, 9f, 8f, 8f, 8f, 7f, 7f, 6f, 6f, 6f, 6f, 6f, 5f, 3f, 2f};

        // Maak een lijstje van studenten aan
        Student[] studentList = StudentListGenerator.generate(testGrades.length).getList();

        // Geef de studenten de test waardes als cijfer
        for (int i = 0; i < testGrades.length; i++) {
            studentList[i].setGrade(testGrades[i]);
        }
        BST<Float, Integer> bst = new BST<>();
        StdRandom.shuffle(studentList);
        for (Student s : studentList) {
            bst.put(s.getGrade(), s.getStudentNumber());
        }

        // Rank elk cijfer
        for (int i = 1; i <= 10; i++) {
            System.out.println("Grade: " + i + ", rank: " + bst.rank((float) i));
        }

        measureQuicksorts();
    }

    private static final int N_EXPERIMENTS = 5;

    private void measureQuicksorts() {
        int[] numberOfStudents = {10000, 20000, 40000, 80000, 160000, 320000, 640000, 1280000};
//        long[][] quicksortResults = new long[numberOfStudents.length][N_EXPERIMENTS];
        long[][] threewayResults = new long[numberOfStudents.length][N_EXPERIMENTS];

        for (int i = 0; i < numberOfStudents.length; i++) {
            for (int j = 0; j < N_EXPERIMENTS; j++) {
//                ResultList list = StudentListGenerator.generate(numberOfStudents[i]);
//                list.shuffle();
//                long startTime = System.currentTimeMillis();
//                list.sort();
//                long endTime = System.currentTimeMillis();
//                quicksortResults[i][j] = (endTime - startTime);

                ResultList list2 = StudentListGenerator.generate(numberOfStudents[i]);
                list2.shuffle();
                long startTime1 = System.currentTimeMillis();
                list2.threewayQuicksort();
                long endTime2 = System.currentTimeMillis();
                threewayResults[i][j] = (endTime2 - startTime1);
            }
        }

//        printPlotLine(numberOfStudents, quicksortResults);
        System.out.println();
        printPlotLine(numberOfStudents, threewayResults);

//        printTable(numberOfStudents, quicksortResults);
        printTable(numberOfStudents, threewayResults);
    }

    private void printTable(int[] numberOfStudents, long[][] results) {

        System.out.println("\\begin{table}[htb]");
        System.out.print("\\begin{tabular}{|");
        for (int i = 0; i < N_EXPERIMENTS + 2; i++) {
            System.out.print(" l |");
        }
        System.out.println("}");
        System.out.println("\\hline");
        System.out.print("Aantal studenten");
        for (int i = 0; i < N_EXPERIMENTS; i++) {
            System.out.print(" & Run " + (i + 1));
        }
        System.out.print(" & Gemiddelde");
        System.out.println("\\\\ \\hline");

        for (int i = 0; i < numberOfStudents.length; i++) {
            System.out.print(numberOfStudents[i]);
            long sum = 0;
            for (int j = 0; j < N_EXPERIMENTS; j++) {
                sum += results[i][j];
                System.out.print(" & " + results[i][j]);
            }
            long average = sum / results[i].length;
            System.out.print(" & " + average);
            System.out.println("\\\\ \\hline");
        }


        System.out.println("\\end{tabular}\n" +
                "\\end{table}");
    }

    private void printPlotLine(int[] numberOfStudents, long[][] threewayResults) {
        for (int j = 0; j < N_EXPERIMENTS; j++) {
            System.out.println("\\addlegendentry{$Run " + (j+1) + "$}");
            System.out.println("\\addplot+[smooth] coordinates");
            System.out.print("{");
            for (int i = 0; i < numberOfStudents.length; i++) {
                System.out.print(String.format("(%d, %s) ", numberOfStudents[i], threewayResults[i][j]));
            }
            System.out.println("};");
        }

    }

}
