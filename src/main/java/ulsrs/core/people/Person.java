package main.java.ulsrs.core.people;

/**
 * The abstract class representing a generic person with basic information.
 */
public abstract class Person {
    /**
     * The first name of the person.
     */
    private String firstName;

    /**
     * The last name of the person.
     */
    private String lastName;

    /**
     * The age of the person.
     */
    private int age;

    /**
     * The address of the person.
     */
    private String address;

    /**
     * Constructs a new Person with the specified details.
     *
     * @param firstName The first name of the person.
     * @param lastName  The last name of the person.
     * @param age       The age of the person.
     * @param address   The address of the person.
     */
    public Person(String firstName, String lastName, int age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    /**
     * Gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName The new first name of the person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName The new last name of the person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age of the person.
     *
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     *
     * @param age The new age of the person.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the address of the person.
     *
     * @return The address of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the person.
     *
     * @param address The new address of the person.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a string containing relevant information about the person.
     *
     * @return A string with the format "firstName, lastName, age, address,"
     */
    public String returnRelevantInfo() {
        return firstName + "," + lastName + "," + age + "," + address + ",";
    }
}