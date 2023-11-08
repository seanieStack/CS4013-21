import java.util.Scanner;
public class Cli {
    


 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in ) ;
    Login login = new Login() ;


    // Code to select diviion 
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
    login.usernameIsCorrect(username) ;
    System.out.println("Please enter your password ");
    String password = scanner.nextLine();
    if (login.usernameIsCorrect(username) && login.pwIsCorrect(username,password) == true ) {
        System.out.println("You have succsessfully logged in ") ;
    } else if ( !login.usernameIsCorrect(username) ) {
        System.out.println("Please enter a valid username , This username is not in the system ") ; 
    } else if ( username.length() != 8 || password.length() != 6 ) {
        System.out.println("Please enter the correct length Username and password , Username is 8 characthers and password is 6 characthers");
    }
}
 }
}
