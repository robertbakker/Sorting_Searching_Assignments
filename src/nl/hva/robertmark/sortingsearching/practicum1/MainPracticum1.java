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
        long startTime = System.nanoTime();
        list.sort();
        long endTime = System.nanoTime();
        long durationQuickSort = (endTime - startTime);

        list.shuffle();
        long startTime1 = System.nanoTime();
        list.threewayQuicksort();
        long endTime2 = System.nanoTime();
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

    private void measureQuicksorts() {
        int[] numberOfStudents = {10000, 20000, 40000, 80000, 160000};
        long[] quicksortResults = new long[numberOfStudents.length];
        long[] threewayResults = new long[numberOfStudents.length];

        for (int i = 0; i < numberOfStudents.length; i++) {
            ResultList list = StudentListGenerator.generate(numberOfStudents[i]);
            list.shuffle();
            long startTime = System.nanoTime();
            list.sort();
            long endTime = System.nanoTime();
            quicksortResults[i] = (endTime - startTime);

            list.shuffle();
            long startTime1 = System.nanoTime();
            list.threewayQuicksort();
            long endTime2 = System.nanoTime();
            threewayResults[i] = (endTime2 - startTime1);
        }

        System.out.println("\\addplot+[smooth] coordinates");
        System.out.print("{");
        for(int i =0; i<quicksortResults.length;i++) {
            System.out.print(String.format("(%d, %s) ", numberOfStudents[i], quicksortResults[i]));
        }
        System.out.println("};");
        System.out.println();
        System.out.println("\\addplot+[smooth] coordinates");
        System.out.print("{");
        for(int i =0; i<threewayResults.length;i++) {
            System.out.print(String.format("(%d, %s) ", numberOfStudents[i], threewayResults[i]));
        }
        System.out.println("};");
    }

}
