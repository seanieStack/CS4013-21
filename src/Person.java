/**
 * Represents a default person with a username and a password.
 */
public abstract class Person {
    String username;
    String password;
    
    /**
     * Constructs a person with the specified username and password.
     *
     * @param username The person's username.
     * @param password The person's password.
     */
    
    public Person(String username, String password){
        this.username = username;
        this.password = password;
    }
}
