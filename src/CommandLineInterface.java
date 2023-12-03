/** 
Command-line interface class for inteacting with App
Handles user input and directs users to the correct functionality based on their divisions(Student, Faculty, Department)
*/
public class CommandLineInterface {
     //Creates object of the Login class
    Login login;

    /** Runs the command-line interface 
    * Creates objects of the different classes based off the user division and passes username and password from Login class to them
    */ 
    
    public void run() {
        //New login object
        login = new Login();
        //Determines division
        switch (login.getDivision()) {
            case "Student" -> {
                //Creates Student object and puts the login details into it
                Student student = new Student(login.getUsername(), login.getPassword());
                student.studentLogic();
            }
            case "Faculty" -> {
                //Creates Faculty object and puts the login details into it
                Faculty faculty = new Faculty(login.getUsername(), login.getPassword());
                faculty.facultyLogic();
            }
            case "Department" -> {
                //Creates Department object and puts the login details into it
                Department department = new Department(login.getUsername(), login.getPassword());
                department.departmentLogic();
            }
            //Displays Error message
            default -> System.out.println("Invalid Input !");
        }

    }

}
