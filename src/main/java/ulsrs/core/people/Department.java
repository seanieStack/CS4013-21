package main.java.ulsrs.core.people;

public class Department extends Person{
    private String departmentId;
    private String department;

    public Department(String firstName, String lastName, int age, String address, String departmentId, String department) {
        super(firstName, lastName, age, address);
        this.departmentId = departmentId;
        this.department = department;
    }

    public String getDepartmentId() {
        return department;
    }

    public void setMemberId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String returnRelevantInfo(){
        return super.returnRelevantInfo() + departmentId + "," + department;
    }
}
