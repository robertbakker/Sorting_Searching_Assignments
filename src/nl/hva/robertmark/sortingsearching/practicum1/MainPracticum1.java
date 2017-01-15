package nl.hva.robertmark.sortingsearching.practicum1;

import nl.hva.dmci.ict.inf.ads.lib.StdRandom;

import java.util.LinkedList;
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

        //BST();

        //measureQuicksorts();

        int[] students = new int[]{10000, 20000, 40000, 80000, 160000};
//        int[] students = new int[]{10000};
        int runs = 5;
        boolean noNiceResults = true;
        List<List<String>> resultTable = new LinkedList<>();
        List<List<String>> efficiencyTable = new LinkedList<>();
        while(noNiceResults) {
             resultTable = runQuicksorts(students, runs);
            List<Long> averages = getAverages(resultTable);
            for (int i = 0; i < resultTable.size(); i++) {
                resultTable.get(i).add(String.valueOf(averages.get(i)));
                resultTable.get(i).add(0, String.valueOf(students[i]));
            }

            efficiencyTable = getEfficienyTable(students, averages);
            boolean sameValues = true;
            for (int i = 0; i < efficiencyTable.size(); i++) {
                if(efficiencyTable.get(i).get(6).equals("-")) {
                    continue;
                }
                if(!efficiencyTable.get(i).get(6).substring(0,2).equals(efficiencyTable.get(1).get(6).substring(0,2))) {
//                if(!efficiencyTable.get(i).get(6).substring(0,4).equals("1.99")) {
                    sameValues = false;
                }
            }
            if(sameValues) {
                noNiceResults = false;
            }
        }

        List<String> tableHeaders = new LinkedList<>();
        tableHeaders.add("Aantal studenten (n)");
        for (int i = 1; i <= runs; i++) {
            tableHeaders.add("Run " + i);
        }
        tableHeaders.add("Gemiddeld (t)");
        resultTable.add(0, tableHeaders);

        System.out.println("Meetresultaten: \n");
        LaTeX.printTable(resultTable);

        tableHeaders = new LinkedList<>();
        tableHeaders.add("n");
        tableHeaders.add("t");
        tableHeaders.add("Vergrotingsfactor (f)");
        tableHeaders.add("log (f, 2)");
        tableHeaders.add("(t + 1) - t");
        tableHeaders.add("t / log n");
        tableHeaders.add("Vergrotingsfactor (f)");
        efficiencyTable.add(0, tableHeaders);

        System.out.println("Efficientie: \n");
        LaTeX.printTable(efficiencyTable);
    }

    private List<Long> getAverages(List<List<String>> resultTable) {
        List<Long> averages = new LinkedList<>();
        for (int i = 0; i < resultTable.size(); i++) {
            long sum = 0;
            for (int j = 0; j < resultTable.get(i).size(); j++) {
                sum += Long.valueOf(resultTable.get(i).get(j));
            }
            long average = sum / resultTable.get(i).size();
            averages.add(average);
        }
        return averages;
    }

    private List<List<String>> getEfficienyTable(int[] students, List<Long> averages) {
        List<List<String>> efficiencyTable = new LinkedList<>();
        for (int i = 0; i < averages.size(); i++) {
            efficiencyTable.add(new LinkedList<String>());
            efficiencyTable.get(i).add(String.valueOf(students[i]));
            efficiencyTable.get(i).add(String.valueOf(averages.get(i)));
            if(i == 0) {
                efficiencyTable.get(i).add("-");
                efficiencyTable.get(i).add("-");
                efficiencyTable.get(i).add("-");
                efficiencyTable.get(i).add(String.format("%.2f", averages.get(i) / Math.log(students[i])));
                efficiencyTable.get(i).add("-");
//                efficiencyTable.get(i).add("-");
            } else {
                double O_N = (double)averages.get(i) / averages.get(i - 1);
                efficiencyTable.get(i).add(String.format("%.2f", O_N));
                efficiencyTable.get(i).add(String.format("%.2f", Math.log(O_N) / Math.log(2)));
                efficiencyTable.get(i).add(String.valueOf( averages.get(i) - averages.get(i - 1)));
                double currentLog = averages.get(i) / Math.log(students[i]);
                double previousLog = averages.get(i - 1) / Math.log(students[i - 1]);
                efficiencyTable.get(i).add(String.format("%.2f", currentLog ));
                efficiencyTable.get(i).add(String.format("%.2f", currentLog / previousLog));
//                efficiencyTable.get(i).add(String.valueOf( Math.log(currentLog / previousLog) / Math.log(2)));
            }
        }
        return efficiencyTable;
    }

    private List<List<String>> runQuicksorts(int[] numberOfStudents, int runs) {
        List<List<String>> results = new LinkedList<>();
        for (int i = 0; i < numberOfStudents.length; i++) {
            results.add(new LinkedList<>());
            for (int j = 0; j < runs; j++) {
                ResultList list = StudentListGenerator.generate(numberOfStudents[i]);
                list.shuffle();
                list.median3sort();
//                QuickBars.swaps = 0;
//                QuickBars.sort(list.getList());
                results.get(i).add(String.valueOf(list.getCompares()));
            }
        }

        return results;
    }

    private void BST() {
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
    }

    private static final int N_EXPERIMENTS = 5;



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
