package main.java.ulsrs.core.people;

public class Faculty extends Person{
    private String employeeId;
    private String department;
    private String title;

    public Faculty(String firstName, String lastName, int age, String address, String employeeId, String department, String title) {
        super(firstName, lastName, age, address);
        this.employeeId = employeeId;
        this.department = department;
        this.title = title;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String returnRelevantInfo(){
        return super.returnRelevantInfo() + employeeId + "," + department + "," + title;
    }
}
