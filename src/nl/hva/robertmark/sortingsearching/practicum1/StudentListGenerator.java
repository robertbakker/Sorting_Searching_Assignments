package nl.hva.robertmark.sortingsearching.practicum1;

/**
 * Created by Robert on 27-11-16.
 */
import java.util.Random;

/**
 *
 * @author Robert
 */
public class StudentListGenerator {

    private static final String GROUP_PREFIX = "I";
    private static final int BASE_STUDENT_NUMBER = 50060001;
    private static final int PER_GROUP = 32;
    public static final String[] STUDIES = {"G", "N", "S", "T"};

    public static ResultList generate(int students) {

        int studies = STUDIES.length;
        int groupsPerStudy = (int)Math.ceil(((double)students / (double)studies) / (double)PER_GROUP);
        int totalGroups = studies*groupsPerStudy;

        ResultList results = new ResultList(students, totalGroups);

        int baseStudentNumber = BASE_STUDENT_NUMBER;
        int groupNumber = 1;
        for (int i = 0; i < students; i++) {

            if(i != 0 && i % studies == 0)
                ++groupNumber;

            if(groupNumber > groupsPerStudy)
                groupNumber = 1;

            String groupName = GROUP_PREFIX + STUDIES[i % studies] + "2" + String.format("%02d", groupNumber);

            float grade = generateGrade();
            int studentNumber = baseStudentNumber++;
            Student s = new Student(groupName,studentNumber,grade);
            results.add(s);
        }

        return results;
    }

    public static float generateGrade() {
        Random rand = new Random();
        return 1 + (rand.nextInt(90) / 10.0f);
    }

}
