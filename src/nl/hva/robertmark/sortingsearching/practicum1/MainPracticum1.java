package nl.hva.robertmark.sortingsearching.practicum1;

/**
 * Created by Robert on 27-11-16.
 */
public class MainPracticum1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        ResultList list = StudentListGenerator.generate(30);
        list.shuffle();
        list.sort();
        System.out.println(list);

        BST<Float, Integer> bst = new BST();
        for (Student s : list.getList()) {
            bst.put(s.getGrade(), s.getStudentNumber());
        }

        for (int i = 1; i <= 10; i++) {


            System.out.println("Grade: " + i + ", rank: " + bst.rank((float) i));
        }


        System.out.println(bst.get(1.2f));

    }

}
