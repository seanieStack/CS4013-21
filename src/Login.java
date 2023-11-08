
public class Login {

//Import SQL Login info 

private String username ;
private String password ;    
private String dbUsername ;
private String dbPassword ;
private String divison ;
/**
 * @param  Username , this is the University number of the user logging in
 * @param  Password , this is the unqiue password of the user logging in 
 */

public Login () {
    username = "" ; // Left empty for now 
    password = "" ;
}


public Login ( String username , String password) {
    this.username = username ;
    this.password = password ;
}
/**
 * @param Divison , This is the division the student belongs to i.e. either Student, Faculty or Department 
 */
public void setDivison (String divison) { // Method to set Divison
    this.divison = divison ;
}

public String getUsernameFromDb(String division, String username) {
    String dbUsername = null;
    String sqlStmt = "SELECT Username FROM users WHERE Division = " + division + "AND Username = "+ username;
    // More code required here to execute statement , probably using DB_Config file 
    return dbUsername;
}

public String getPwFromDb(String division, String username) {
    String dbPw = null;
    String sqlStmt = "SELECT password FROM users WHERE Division = " + division + "AND Username = "+ username;
    // More code required here to execute statement , probably using DB_Config file 
    return dbPw;
}





public boolean usernameIsCorrect( String username) { // Needs Divison search for both username and PW methods 
boolean state = false ;   
getUsernameFromDb( divison , username) ; 
if ( username == getUsernameFromDb( divison , username) ) {
    state = true ;
    }
   return state ;
}

public boolean pwIsCorrect( String username ,String password) {
    boolean state = false ;    
    if ( password == getPwFromDb(divison, username) ) {
        state = true ;
        }
       return state ;
    }




}