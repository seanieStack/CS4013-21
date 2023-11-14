import java.util.ArrayList;
import java.util.List;


public class Login  {

private String username;
private String password;
private String division;

CsvReader reader = new CsvReader(); // Create a CSV reader object to validate login details
/**
 * @param  Username , this is the University number of the user logging in
 * @param  Password , this is the unqiue password of the user logging in 
 */

public Login () {
    username = "" ; // Left empty for now 
    password = "" ;
}



public Login ( String username , String password) { //Default constructor 
    this.username = username ;
    this.password = password ;
}
/**
 * @param Divison , This is the division the student belongs to i.e. either Student, Faculty or Department 
 */
public void setDivision(String division) { // Method to set Divison
    this.division = division;
}

public String getDivision() { //Method to get divison
    return division;
}

public void setUsername( String username) {
    this.username = username;
}

public String getUsername() {
    return username;
}


public boolean correctLogin(String searchUsername, String searchPassword, String searchDivision) {
    List<String[]> searchResults = new ArrayList<>();
    boolean loggedIn = false ; 
    for (String[] row : reader.CsvSearch("Data/LoginInfo.csv")) { // For reference this is how filepaths should be formatted 
        // Assuming the order is username, password, division
        String username = row[0];
        String password = row[1];
        String division = row[2];

        // Check if the row matches the search criteria
        if (username.equals(searchUsername) && password.equals(searchPassword) && division.equals(searchDivision)) {
            searchResults.add(row);
            loggedIn = true;
        }
    }
    return loggedIn;
}

public boolean correctDivison(String searchUsername, String searchPassword, String searchDivision) {
    for (String[] row : reader.CsvSearch("Data/LoginInfo.csv")) {
        // Assuming the order is username, password, division
        String username = row[0];
        String password = row[1];
        String division = row[2];

        // Check if the row matches the search criteria
        if (username.equals(searchUsername) && password.equals(searchPassword) && !division.equals(searchDivision)) {
            return true; // Return true immediately if a match is found
        }
    }
    return false; // Return false if no match is found
}
// Edit these methods later to avvoid code duplication

public boolean isStudent(String studentNumber, String searchDivision) {
    List<String[]> searchResults = new ArrayList<>(); 
    for (String[] row : reader.CsvSearch("Data/LoginInfo.csv")) { // For reference this is how filepaths should be formatted 
        // Assuming the order is username, password, division
        String username = row[0];
    
        String division = row[2];

        // Check if the row matches the search criteria
        if (username.equals(studentNumber)  && division.equals(searchDivision)) {
            searchResults.add(row);
            return true ;
        }
    }
    return false ;
}
}
