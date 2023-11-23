import java.util.Scanner;


public class Login  {

    private String username;
    private String password;
    private String division;
    Scanner scanner;

    CsvReader reader;


    public Login () {
        scanner = new Scanner(System.in);
        reader = new CsvReader();
        setupDivision();
        setupLogin();
    }

    public void setupLogin(){
        boolean loggedIn = false;
        while(!loggedIn) {

            System.out.println("Please enter your username (University Number) ");
            username = scanner.nextLine();

            System.out.println("Please enter your password ");
            password = scanner.nextLine();

            if (correctLogin(username, password, division)) {
                System.out.println("You have successfully logged in !");
                loggedIn = true;
            } else if (username.length() != 8 || password.length() != 6) {
                System.out.println("Please enter the correct length Username and password , Username is 8 characters and password is 6 characters");
            } else {
                System.out.println("Please enter the correct username and password !");
            }
        }
    }

    public void setupDivision() {
        boolean selectionPassed = false;
        while (!selectionPassed) {
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
                default -> System.out.println("Invalid command line Usage , Please select a valid option ");
            }
        }
    }
    public boolean correctLogin(String searchUsername, String searchPassword, String searchDivision) {
        for (String[] row : reader.CsvSearch("./src/data/LoginInfo.csv")) {
            String username = row[0];
            String password = row[1];
            String division = row[2];

            if (username.equals(searchUsername) && password.equals(searchPassword) && division.equals(searchDivision)) {
                return true;
            }
        }
        return false;
    }
    // Edit these methods later to avoid code duplication

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

        public String getDivision() {
            return division;
        }
        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
    }
}
