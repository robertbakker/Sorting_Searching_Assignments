package nl.hva.robertmark.sortingsearching.practicum1;

/**
 * Created by Robert on 27-11-16.
 */
public class MainPracticum1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

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
    }

}
