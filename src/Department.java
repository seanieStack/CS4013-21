import java.util.Calendar;
import java.util.Scanner;

public class Department extends Person{
    Scanner scanner;
    Transcript transcript;
    Login login;
    public Department(String username, String password){
        super(username, password);
        scanner = new Scanner(System.in);
        transcript = new Transcript();
        login = new Login();
    }

    public void departmentLogic(){

        System.out.print("Use command (S) to (S)ubmit results");
        String commandSubmit = scanner.nextLine().toUpperCase();

        if (commandSubmit.equals("S")) {

            System.out.println("Enter Module code");
            String moduleCode = scanner.nextLine().toUpperCase();

            if (moduleCode.length() != 6)
                System.out.println("Module code are 6 digits in length ! please reenter");
            else if (!Modules.searchModules(moduleCode))
                System.out.println("Module not found");

            System.out.println("Enter student Number");
            String selectedStudentNumber = scanner.nextLine();
            if (!login.isStudent(selectedStudentNumber, "Student")|| selectedStudentNumber.length() != 8) {
                System.out.println("Invalid Student number ! Please enter a correct one ") ;
            } else if (Modules.resultsExists(selectedStudentNumber, moduleCode)){
                System.out.println("Results for  student ( " + selectedStudentNumber +" ) already exist for module ( "+ moduleCode + " )");
            }

            boolean correctGrade = false;
            String grade = "";
            while(!correctGrade) {
                System.out.println("Enter Grade e.g A1");
                grade = scanner.nextLine().toUpperCase();
                if (Modules.validGrade(grade)) {
                    correctGrade = true;
                }
                else {
                    System.out.println("This is not a valid grade , Please see UL handbook of academic regulations for valid grades ");
                }
            }
            boolean correctYear = false ;
            String ay = "       ";
            while (!correctYear) {
            System.out.println("Please enter the AY year in which this grade was taken");
             ay = scanner.nextLine().toUpperCase();
            if(ay.length() == 9 && ay.charAt(4) == '/')  { //Comment: Not fully correct but covers most cases 
                correctYear = true;
            }
            String semester = Modules.getModuleSemester(moduleCode);
            Modules.submitResults(selectedStudentNumber, moduleCode, semester, ay, grade);
            System.out.println("Grade for" + moduleCode + "is now reflected on the transcript of student" + username  );
        }
        }
    }
}
