import java.util.Scanner;
public class CommandLineInterface {

    Scanner scanner;
    Login login;
    QcaCalc qcaCalculator;
    Transcript transcript;
    Faculty faculty;

    String username;
    String password;

    public void setup(){
        scanner = new Scanner(System.in);
        login = new Login();
        qcaCalculator = new QcaCalc();
        transcript = new Transcript();
        faculty = new Faculty();

        username = "";
        password = "";

        run();
    }

    private void run() {

        setDivision();
        login();

        switch (login.getDivision()) {
            case "Student" -> {
                Student student = new Student(username, password);
                student.studentLogic();
            }
            case "Faculty" -> {
                Faculty faculty = new Faculty(username, password);
                faculty.facultyLogic();
            }
            case "Department" -> {
                Deparment deparment = new Deparment(username, password);
                deparment.departmentLogic();
            }
            default -> System.out.println("Invalid Input !");
        }

    }

    private void setDivision() {
        boolean selectionPassed = false; //Fix: This was working , now can continue even without sucsessful login , Too tired to fix now
        while (!selectionPassed) {
            System.out.println("Choose your division , (S)tudent , (F)aculty , (D)epartment");
            String command = scanner.nextLine().toUpperCase();
            switch (command) {
                case "S" -> {
                    login.setDivision("Student");
                    selectionPassed = true;
                }
                case "F" -> {
                    login.setDivision("Faculty");
                    selectionPassed = true;
                }
                case "D" -> {
                    login.setDivision("Department");
                    selectionPassed = true;
                }
                default -> System.out.println("Invalid command line Usage , Please select a valid option ");
            }
        }
    }

    private void login(){
        boolean loggedIn = false;
        while(!loggedIn) {

            System.out.println("Please enter your username (University Number) ");
            username = scanner.nextLine();
            login.setUsername(username);

            System.out.println("Please enter your password ");
            password = scanner.nextLine();

            if (login.correctLogin(username, password, login.getDivision())) {
                System.out.println("You have successfully logged in !");
                loggedIn = true;
            } else if (username.length() != 8 || password.length() != 6) {
                System.out.println("Please enter the correct length Username and password , Username is 8 characters and password is 6 characters");
            } else {
                System.out.println("Please enter the correct username and password !");
            }
        }
    }
}




