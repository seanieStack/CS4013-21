import java.util.Scanner;
public class Cli {
    


 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in ) ;
    Login login = new Login() ;
    QcaCalc qcaCalculator = new QcaCalc() ;
    Transcript transcript = new Transcript() ;

   // System.out.println(transcript.printTranscriptModules);
   
    boolean selectionPassed = false ;
    System.out.println("Choose your division , (S)tudent , (F)aculty , (D)epartment");
    System.out.println("----------------------------------------------------------------");
    String command = scanner.nextLine().toUpperCase();
    if ( command.equals("S")) {
        login.setDivison ("Student") ;
        selectionPassed = true ;
    } else if ( command.equals("F")) {
        login.setDivison("Faculty");
        selectionPassed = true ;
    } else if ( command.equals ("D")) {
        login.setDivison("Department");
        selectionPassed = true ;
    } else {
        System.out.println ("Invalid command line Usage , Please select a valid option " );
         selectionPassed = false ;
    }

    if (selectionPassed == true ) {
    System.out.println("Please enter your username (University Number) ");
    System.out.println("----------------------------------------------------------------");

    String username = scanner.nextLine();
    login.setUsername(username);
    
    System.out.println("Please enter your password ");
    System.out.println("----------------------------------------------------------------");
    String password = scanner.nextLine();
    if (login.correctLogin(username, password,login.getDivison()) == true ) {
        System.out.println("You have succsessfully logged in ! ") ;
        System.out.println("----------------------------------------------------------------");
    } else if ( username.length() != 8 || password.length() != 6 ) {
        System.out.println("Please enter the correct length Username and password , Username is 8 characthers and password is 6 characthers");
    } 
    else if ( login.correctDivison(username, password, login.getDivison())) { // Boolean states may need some work
        System.out.println( "This username and password belongs to the " + login.getDivison() + " division, not the division you have chosen"); // This line is incorrect
    }
    else {
        System.out.println("Please enter the correct username and password !");
    } 
}


    String division =  login.getDivison();
    if (division.equals("Student")) {
    System.out.println("Enter your command , (C)alculate QCA , (V)iew Transcript,(R)equest action" );
    System.out.println("----------------------------------------------------------------");
    String commandStudent = scanner.nextLine().toUpperCase();
    if( commandStudent.equals("C") ) {
        System.out.println( " Please enter this semesters grades in the format ,A1 ,A2,B1") ;
        String gradesInput = scanner.nextLine() ;
        String[] grades = gradesInput.split(",\\s*"); // This line needs some editing 
        System.out.println("Cummulative QCA is " + String.format("%.2f",qcaCalculator.getCummulativeQca(grades)) + " for the " +grades.length + " modules you have taken" ); 
    } else if (commandStudent.equals("V")) {
       System.out.println(transcript.printTranscript(login.getUsername())) ;
    }


    if (commandStudent.equals("R")){
        System.out.println("Enter your command, (R)equest repeat , (L)ink in module , (Y)ear repeat , (S)emester repeat") ;
        System.out.println("----------------------------------------------------------------");
        String commandRequest = scanner.nextLine() ; 
        if (commandRequest.equals("R")) {
            System.out.println("Enter Module code for your requested module");
            System.out.println("----------------------------------------------------------------");
            String commandModule = scanner.nextLine().toUpperCase(); //6 Digit length 
            //Chceck module is on transcript , if so add this to student transcript 
            // Add to student transcript method
            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
            transcript.setRequest(" has requested to repeat module: "+ commandModule );
            transcript.recordRequest(); // Add to CSV file 
        } else if (commandRequest.equals("L")) {
            System.out.println("Enter Module code for your requested Link-in module");
            System.out.println("----------------------------------------------------------------");
            String commandModule = scanner.nextLine().toUpperCase(); // 6 Digit length 
            transcript.setRequest("has requested to take module : " + commandModule + " as a link in module");
            transcript.recordRequest(); // Add to CSV file 
            // Add to student transcript method
            System.out.println("Your link in module request has being registered and is reflected on your transcript ");
        } else if (commandRequest.equals("Y")) {
            System.out.println("Enter the academic year in which you wish to repeat");
            System.out.println("----------------------------------------------------------------");
            String commandYear = scanner.nextLine().toUpperCase();
            //Chceck module is on transcript , if so add this to student transcript 
            // Add to student transcript method
            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
            transcript.recordRequest(); // Add to CSV file 
            transcript.setRequest(" has requested to repeat AY : " + commandYear );
        } else if (commandRequest.equals("S")) {
            System.out.println("Enter the Semester and AY in which you wish to repeat");
            System.out.println("----------------------------------------------------------------");
            String commandSemester = scanner.nextLine().toUpperCase();
            //Chceck module is on transcript , if so add this to student transcript 
            // Add to student transcript method
            transcript.setRequest(" has requested to repeat semester : " +commandSemester);
            transcript.recordRequest(); // Add to CSV file 
            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
        }
    }
    // Need logic to prevent passing students from requesting repeats 
}
    if (division.equals("Faculty")) {
    System.out.println("Please enter the student number to submit results" ); // Need to review the flowchart and specs
    String commandFaculty = scanner.nextLine().toUpperCase();
    }

    if (division.equals("Department")) {
        System.out.println("Please enter the student number to submit results" ); // Need to review the flowchart and specs
        String studentNumber = scanner.nextLine();
        if (!login.isStudent(studentNumber, "Student")) {
            System.out.println("Invalid Student number ! Please enter a correct one ") ;

        }
        }
 }
}
