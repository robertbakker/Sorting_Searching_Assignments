package nl.hva.robertmark.sortingsearching.practicum1;

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
        ResultList list = StudentListGenerator.generate(10000);
        list.shuffle();
        long startTime = System.currentTimeMillis();
        list.sort();
        long endTime = System.currentTimeMillis();
        long durationQuickSort = (endTime - startTime);

        list.shuffle();
        long startTime1 = System.currentTimeMillis();
        list.threewayQuicksort();
        long endTime2 = System.currentTimeMillis();
        long durationThreeway = (endTime2 - startTime1);

        System.out.println(list);


        BST<Float, Integer> bst = new BST<>();
        for (Student s : list.getList()) {
            bst.put(s.getGrade(), s.getStudentNumber());
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println("Grade: " + i + ", rank: " + bst.rank((float) i));
        }
        System.out.println(bst.get(1.2f));
        System.out.println("Duration of Quicksort: " + durationQuickSort);
        System.out.println("Duration of ThreewaySort: " + durationThreeway);

        measureQuicksorts();
    }

    private static final int N_EXPERIMENTS = 1;

    private void measureQuicksorts() {
        int[] numberOfStudents = {10000, 20000, 40000, 80000, 160000};
        long[][] quicksortResults = new long[numberOfStudents.length][N_EXPERIMENTS];
        long[][] threewayResults = new long[numberOfStudents.length][N_EXPERIMENTS];

        for (int i = 0; i < numberOfStudents.length; i++) {
            for (int j = 0; j < N_EXPERIMENTS; j++) {
                ResultList list = StudentListGenerator.generate(numberOfStudents[i]);
                list.shuffle();
                long startTime = System.currentTimeMillis();
                list.sort();
                long endTime = System.currentTimeMillis();
                quicksortResults[i][j] = (endTime - startTime);

                ResultList list2 = StudentListGenerator.generate(numberOfStudents[i]);
                list2.shuffle();
                long startTime1 = System.currentTimeMillis();
                list2.threewayQuicksort();
                long endTime2 = System.currentTimeMillis();
                threewayResults[i][j] = (endTime2 - startTime1);
            }
        }

        printPlotLine(numberOfStudents, quicksortResults);
        System.out.println();
        printPlotLine(numberOfStudents, threewayResults);

        printTable(numberOfStudents, quicksortResults);
    }

    private void printTable(int[] numberOfStudents, long[][] results) {

        System.out.println("\\begin{table}[]");
        System.out.print("\\begin{tabular}{|");
        for (int i = 0; i < N_EXPERIMENTS + 2; i++) {
            System.out.print("1|");
        }
        System.out.println("}");
        System.out.println("\\hline");
        System.out.print("Aantal studenten");
        for (int i = 0; i < N_EXPERIMENTS; i++) {
            System.out.print(" & Run " + (i+1));
        }
        System.out.print(" & Gemiddelde");
        System.out.println("\\\\ \\hline");

        for(int i = 0; i < numberOfStudents.length; i++) {
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
        System.out.println("\\addplot+[smooth] coordinates");
        System.out.print("{");
        for (int i = 0; i < threewayResults.length; i++) {
            long sum = 0;
            for (long j : threewayResults[i]) sum += j;
            long average = sum / threewayResults[i].length;
            System.out.print(String.format("(%d, %s) ", numberOfStudents[i], average));
        }
        System.out.println("};");
    }

}
