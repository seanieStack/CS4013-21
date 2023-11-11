package main.java.ulsrs.core.people;

/**
 * Represents a department, extending the abstract class Person with additional attributes specific to departments.
 */
public class Department extends Person {
    /**
     * The unique identifier for the department.
     */
    private String departmentId;

    /**
     * The name of the department.
     */
    private String department;

    /**
     * Constructs a new Department with the specified details.
     *
     * @param firstName     The first name of the department representative.
     * @param lastName      The last name of the department representative.
     * @param age           The age of the department representative.
     * @param address       The address of the department representative.
     * @param departmentId  The unique identifier for the department.
     * @param department    The name of the department.
     */
    public Department(String firstName, String lastName, int age, String address, String departmentId, String department) {
        super(firstName, lastName, age, address);
        this.departmentId = departmentId;
        this.department = department;
    }

    /**
     * Gets the unique identifier of the department.
     *
     * @return The department ID.
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the unique identifier of the department.
     *
     * @param departmentId The new department ID.
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Gets the name of the department.
     *
     * @return The name of the department.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the name of the department.
     *
     * @param department The new name of the department.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Overrides the returnRelevantInfo method in the Person class to include department-specific information.
     *
     * @return A string with relevant information about the department.
     */
    @Override
    public String returnRelevantInfo() {
        return super.returnRelevantInfo() + departmentId + "," + department;
    }
}