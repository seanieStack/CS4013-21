package main.java.ulsrs.core.people;

/**
 * Represents a faculty member, extending the abstract class Person with additional attributes specific to faculty.
 */
public class Faculty extends Person {
    /**
     * The unique identifier for the faculty member.
     */
    private String employeeId;

    /**
     * The department to which the faculty member belongs.
     */
    private String department;

    /**
     * The title or position of the faculty member.
     */
    private String title;

    /**
     * Constructs a new Faculty member with the specified details.
     *
     * @param firstName   The first name of the faculty member.
     * @param lastName    The last name of the faculty member.
     * @param age         The age of the faculty member.
     * @param address     The address of the faculty member.
     * @param employeeId  The unique identifier for the faculty member.
     * @param department  The department to which the faculty member belongs.
     * @param title       The title or position of the faculty member.
     */
    public Faculty(String firstName, String lastName, int age, String address, String employeeId, String department, String title) {
        super(firstName, lastName, age, address);
        this.employeeId = employeeId;
        this.department = department;
        this.title = title;
    }

    /**
     * Gets the unique identifier of the faculty member.
     *
     * @return The employee ID of the faculty member.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the unique identifier of the faculty member.
     *
     * @param employeeId The new employee ID for the faculty member.
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the department to which the faculty member belongs.
     *
     * @return The department of the faculty member.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department to which the faculty member belongs.
     *
     * @param department The new department for the faculty member.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets the title or position of the faculty member.
     *
     * @return The title of the faculty member.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title or position of the faculty member.
     *
     * @param title The new title or position for the faculty member.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Overrides the returnRelevantInfo method in the Person class to include faculty-specific information.
     *
     * @return A string with relevant information about the faculty member.
     */
    @Override
    public String returnRelevantInfo() {
        return super.returnRelevantInfo() + employeeId + "," + department + "," + title;
    }
}