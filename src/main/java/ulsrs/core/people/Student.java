package main.java.ulsrs.core.people;

/**
 * Represents a student, extending the abstract class Person with additional attributes specific to students.
 */
public class Student extends Person {
    /**
     * The unique identifier for the student.
     */
    private String studentId;

    /**
     * The course in which the student is enrolled.
     */
    private String course;

    /**
     * The Quality Credit Average (QCA) of the student.
     */
    private double QCA;

    /**
     * Constructs a new Student with the specified details.
     *
     * @param firstName The first name of the student.
     * @param lastName  The last name of the student.
     * @param age       The age of the student.
     * @param address   The address of the student.
     * @param studentId The unique identifier for the student.
     * @param course    The course in which the student is enrolled.
     * @param QCA       The Quality Credit Average (QCA) of the student.
     */
    public Student(String firstName, String lastName, int age, String address, String studentId, String course, double QCA) {
        super(firstName, lastName, age, address);
        this.studentId = studentId;
        this.course = course;
        this.QCA = QCA;
    }

    /**
     * Gets the student's unique identifier.
     *
     * @return The student's unique identifier.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's unique identifier.
     *
     * @param studentId The new unique identifier for the student.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the course in which the student is enrolled.
     *
     * @return The course of the student.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the course in which the student is enrolled.
     *
     * @param course The new course for the student.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Gets the Quality Credit Average (QCA) of the student.
     *
     * @return The Quality Credit Average (QCA) of the student.
     */
    public double getQCA() {
        return QCA;
    }

    /**
     * Sets the Quality Credit Average (QCA) of the student.
     *
     * @param QCA The new Quality Credit Average (QCA) for the student.
     */
    public void setQCA(double QCA) {
        this.QCA = QCA;
    }

    /**
     * Overrides the returnRelevantInfo method in the Person class to include student-specific information.
     *
     * @return A string with relevant information about the student.
     */
    @Override
    public String returnRelevantInfo() {
        return super.returnRelevantInfo() + studentId + "," + course + "," + QCA;
    }
}