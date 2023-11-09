package main.java.ulsrs.core.people;

public class Student extends Person{
    private String studentId;
    private String course;
    private double QCA;

    public Student(String firstName, String lastName, int age, String address, String studentId, String course, double QCA) {
        super(firstName, lastName, age, address);
        this.studentId = studentId;
        this.course = course;
        this.QCA = QCA;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getQCA() {
        return QCA;
    }

    public void setQCA(double QCA) {
        this.QCA = QCA;
    }
}
