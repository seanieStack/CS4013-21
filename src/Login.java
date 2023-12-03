import java.util.Scanner;

/** Class handles user authentication and the selection of the division
*relies on the CSV files for storing these values
* prompts user to enter username and password and can belong to any one of the 3 divisions(Student, Faculty, Department)
*/


public class Login  {

    private String username;
    private String password;
    private String division;
    Scanner scanner;
    CsvReader reader;

    /**Constructor for Login class
    * Initializes CSV reader/scanner
    * Sets up login details/division for the user
    */


    public Login () {
        scanner = new Scanner(System.in);
        reader = new CsvReader();
        setupDivision();
        setupLogin();
    }

     /** Prompts user to enter username and password until message is printed stating login was successful
     *the login success is decided by correct username, password and division
     */
    
    public void setupLogin(){
        boolean loggedIn = false;
        //Keeps looping until user provides valid login details
        while(!loggedIn) {

            System.out.println("Please enter your username (University Number) ");
            username = scanner.nextLine();

            System.out.println("Please enter your password ");
            password = scanner.nextLine();

            if (correctLogin(username, password, division)) {
                System.out.println("You have successfully logged in !");
                loggedIn = true;
            } 
            //Username must be 8 chars; password must be 6 chars
            else if (username.length() != 8 || password.length() != 6) {
                System.out.println("Please enter the correct length Username and password , Username is 8 characters and password is 6 characters");
            } else {
                System.out.println("Please enter the correct username and password !");
            }
        }
    }

    /**Prompts user to choose their division (Student, Faculty, Department) 
    *Once user inputs one of the divisions, it brings them to the correct page
    *If selection is invalid, it prints line and gets user to choose their *division again
    */   
    
    public void setupDivision() {
        boolean selectionPassed = false;
        //Must make sure that division is slected before continuing 
        while (!selectionPassed) 
        //Gives user option to press 'S,F,D' for their selected division
        {
            System.out.println("Choose your division , (S)tudent , (F)aculty , (D)epartment");
            String command = scanner.nextLine().toUpperCase();
            switch (command) {
                case "S" -> {
                    division = "Student";
                    selectionPassed = true;
                }
                case "F" -> {
                    division = "Faculty";
                    selectionPassed = true;
                }
                case "D" -> {
                    division = "Department";
                    selectionPassed = true;
                }
                //Sends user  back to select another option
                default -> System.out.println("Invalid command line Usage , Please select a valid option ");
            }
        }
    }

    /**Prints if username, password and division that is provided is correct
    *@param searchUsername -username to be verified 
    *@param searchPassword -password that matches the username
    *@param searchDivision -division of the user
    *@return true if login is correct, false if login is incorrect 
    */
    
    public boolean correctLogin(String searchUsername, String searchPassword, String searchDivision) {
        //iterates throughthe CSV data
        for (String[] row : reader.CsvSearch("./src/data/LoginInfo.csv")) {
            String username = row[0];
            String password = row[1];
            String division = row[2];
            //Compares username, password and division info provided by user to check if they are the same as the stored ones
            if (username.equals(searchUsername) && password.equals(searchPassword) && division.equals(searchDivision)) {
                return true;
            }
        }
        return false;
    }
  
     /**Checks if a student is in a particular division
     *@param studentNumber -number of the student used to check within division
     *@param searchDivision -division to search for student within
     *@return true if student is found in division, false if student is not found in division
     */
    
    public boolean isStudent(String studentNumber, String searchDivision) {
        for (String[] row : reader.CsvSearch("./src/data/LoginInfo.csv")) {
            String username = row[0];
            String division = row[2];

            if (username.equals(studentNumber)  && division.equals(searchDivision)) {
                return true;
            }
        }
        return false;
    }

        /**Gets the division (Student, Faculty, Department)
        *@return the division 
	    */ 
    
        public String getDivision() {
            return division;
        }

        /**Gets the username 
        *@return the username
	    */
    
        public String getUsername() {
            return username;
        }

        /**Gets the password 
        *@return the password 
	    */
    
        public String getPassword() {
            return password;
    }
}
