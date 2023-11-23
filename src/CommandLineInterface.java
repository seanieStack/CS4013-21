public class CommandLineInterface {
    Login login;
    public void run() {

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
                Department department = new Department(login.getUsername(), login.getPassword());
                department.departmentLogic();
            }
            default -> System.out.println("Invalid Input !");
        }

    }

}