package nl.hva.robertmark.sortingsearching.practicum1;

/**
 * @author Robert
 */
public class Student implements Comparable<Student> {

    private String group;
    private int studentNumber;
    private float grade;

    public Student(String group, int studentNumber, Float grade) {
        this.group = group;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    // Getters and setters

    @Override
    public int compareTo(Student that) {
        if (this.grade < that.getGrade()) return 1;
        if (this.grade > that.getGrade()) return -1;
        if(this.studentNumber > that.getStudentNumber()) return -1;
        if(this.studentNumber < that.getStudentNumber()) return 1;

        return 0;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" + "group=" + group + ", studentNumber=" + studentNumber + ", grade=" + grade + '}';
    }
}
