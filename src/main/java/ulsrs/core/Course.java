package main.java.ulsrs.core;

public class Course {
    private String courseName;
    private int nfqLevel;
    private String courseID;

    public Course(String courseName, int nfqLevel, String courseID){
        this.courseName = courseName;
        this.nfqLevel = nfqLevel;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getNfqLevel() {
        return nfqLevel;
    }

    public void setNfqLevel(int nfqLevel) {
        this.nfqLevel = nfqLevel;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
