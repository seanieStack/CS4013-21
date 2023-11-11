import java.util.Scanner;
public class Cli {
    


 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in ) ;
    Login login = new Login() ;
    QcaCalc qcaCalculator = new QcaCalc() ;

   
    boolean selectionPassed = false ;
    System.out.println("Choose your division , (S)tudent , (F)aculty , (D)epartment");
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
    String username = scanner.nextLine();
    
    System.out.println("Please enter your password ");
    String password = scanner.nextLine();
    if (login.correctLogin(username, password,login.getDivison()) == true ) {
        System.out.println("You have succsessfully logged in ") ;
    } else if ( username.length() != 8 || password.length() != 6 ) {
        System.out.println("Please enter the correct length Username and password , Username is 8 characthers and password is 6 characthers");
    } 
    else if ( login.correctDivison(username, password, login.getDivison())) { // Boolean states may need some work
        System.out.println( "This username and password belongs to the " + login.getDivison() + " division, not the division you have chosen");
    }
    else {
        System.out.println("Please enter the correct username and password (!");
    } 
}
    System.out.println("Enter your command , (C)alculate QCA , (V)iew Transcript,(R)equest repeat" );
    String command2 = scanner.nextLine().toUpperCase();
    if( login.getDivison() .equals("Student") && command2.equals("C") ) {
        System.out.println( " Please enter this semesters grades in the format ,A1 ,A2,B1") ;
        String gradesInput = scanner.nextLine() ;
        String[] grades = gradesInput.split(",\\s*"); // This line needs some editing 
        System.out.println("Cummulative QCA is " + String.format("%.2f",qcaCalculator.getCummulativeQca(grades)) + " for the " +grades.length + " modules you have taken" ); 
    }
  

 }
}
