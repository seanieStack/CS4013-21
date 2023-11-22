import java.util.Scanner;
public class CommandLineInterface {

    Scanner scanner;
    Login login;

    public void setup(){
        scanner = new Scanner(System.in);

        run();
    }

    private void run() {

        login = new Login();

        switch (login.getDivision()) {
            case "Student" -> {
                Student student = new Student(login.getUsername(), login.getPassword());
                student.studentLogic();
            }
            case "Faculty" -> {
                Faculty faculty = new Faculty(login.getUsername(), login.getPassword());
                faculty.facultyLogic();
            }
            case "Department" -> {
                Deparment deparment = new Deparment(login.getUsername(), login.getPassword());
                deparment.departmentLogic();
            }
            default -> System.out.println("Invalid Input !");
        }

    }

}




