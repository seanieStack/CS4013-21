import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with the formatting of the student Transcript.
 */

public class Transcript {
    // This class deals with all the formatting of the student Transcript 
    private String action ;

    CsvReader reader = new CsvReader();
    CsvWriter writer = new CsvWriter();

      /**
     * Prints the provided text centered within a given width.
     *
     * @param text  The text to be centered.
     * @param width The width within which the text should be centered.
     * @return      The centered text.
     */

    public String printCentered(String text, int width) {
        int padSize = width - text.length();
        int padStart = text.length() + padSize / 2;
    
        text = String.format("%" + padStart + "s", text);
        text = String.format("%-" + width  + "s", text);
        
        return text;
    }
    
     /**
     * Returns the current date in the ("dd/MMM/yyyy") format.
     *
     * @return The current date.
     */
    
    public String currentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return today.format(formatter);

    }

    // Row ordering 3 = Gender, 4 = Firstname , 5 = Lastname , 6= Address 1 ,7 = Address 2 , 8 = Address 3 , 9 = Telephone ,10 = course , 11 = route 
    /**
     * Gets the data from the specified column for a specific student.
     *
     * @param studentNumber The student number.
     * @param columnIndex   The index of the column.
     * @return The data from the specified column.
     */
    
    public String getData(String studentNumber, int columnIndex) {
        for (String[] row : reader.CsvSearch("./src/data/LoginInfo.csv")) {
            String username = row[0];
            if (username.equals(studentNumber) && row.length > columnIndex) {
                return row[columnIndex]; // Return the data from the specified column
            }
        }
        return ""; // Return an empty string instead of null
    }

    /**
     * Gets the results from the specified column for a specific student.
     *
     * @param studentNumber The student number.
     * @param columnIndex   The index of the column.
     * @return The results from the specified column.
     */
    
    public String getResultsData(String studentNumber, int columnIndex) {
        for (String[] row : reader.CsvSearch("./src/data/StudentResults.csv")) {
            String username = row[0];
            if (username.equals(studentNumber) && row.length > columnIndex) {
                return row[columnIndex]; // Return the data from the specified column
            }
        }
        return ""; // Return an empty string instead of null
    }

    /**
     * Finds the abbreviation for a specified student based on gender.
     *
     * @param studentNumber The student number.
     * @return The abbreviation: ("MR" or "MS").
     */
    
    public String abbreviation(String studentNumber) {
        return getData(studentNumber,3).equals("Male") ? "MR" : "MS";
    }

    /**
     * Sets the request action.
     *
     * @param action The request action.
     */
    
    public void setRequest (String action ) { 
         this.action = action;
    }

    /**
     * Gets the request action.
     *
     * @return The request action.
     */
    
    public String getRequest () {
        return action;
    }

      /**
     * Formats the request action.
     *
     * @return The formatted request action.
     */
    
    public String formatRequest() {      
        return  "Student" + getRequest();
    }
    

       /**
     * Records the request for a provided username.
     *
     * @param username The username.
     */
    
    public void recordRequest(String username) {
        String request = formatRequest();
        writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"1", request);
    }

    /**
     * Sets the request result for a provided username.
     *
     * @param approved Whether the request is approved(true/false).
     * @param username The username.
     */
    
    public void setRequestResult(boolean approved,String username) {
        if (approved) {
            writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"2", "Approved");
        } else {
            writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"2" ,"Denied");
        }
    }

     /**
     * Sets the request comments for a provided username.
     *
     * @param username The username.
     * @param comments The comments.
     */
    
    public void setRequestComments(String username , String comments ){
        writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"3", comments);
    }

    /**
     * Gets data from multiple columns for a provided username and semester.
     *
     * @param username The username.
     * @param semester The semester.
     * @return Data from multiple columns for the provided student and semester.
     */ 
    
    public String getMultipleColumns(String  username, String semester ) {
        List<String[]> csvData = reader.CsvSearch("./src/data/StudentGrades.csv");

        return csvData.stream()
            .filter( row -> row.length > 4 && row[0].equals(username) && row[2].equals(semester)  )
            .map(row -> String.join("         ", row[1], row[2], row[3], row[4]))
            .collect(Collectors.joining("\n"));
    }

        /**
     * Prints the complete student transcript for the provided student number.
     *
     * @param studentNumber The student number.
     * @return The formatted student transcript.
     */

    public String printTranscript (String studentNumber) {
        String headerText  = ("Student Transcript");
        String outputHeaderText = printCentered(headerText, 98);

        return outputHeaderText +
                "\n" +
                "University of Limerick\n" +
                currentDate() +
                " Full Student Transcript " + studentNumber +
                "\n" +
                "\n" +
                "Name: " +
                abbreviation(studentNumber) + " " + getData(studentNumber, 4) + " " + getData(studentNumber, 5) +
                "\n" +
                "Address: " +
                getData(studentNumber, 6) +
                "\n" +
                getData(studentNumber, 7) +
                "\n" +
                getData(studentNumber, 8) +
                "\nTelephone:" +
                getData(studentNumber, 9) +
                "\n\n" +
                "Status: " +
                getData(studentNumber, 10) +
                "\nCourse: " +
                getData(studentNumber, 10) +
                "\n" +
                "Programme: " +
                getData(studentNumber, 10) +
                "\n" +
                "Route: " +
                getData(studentNumber, 11) +
                "\n" +
                "Request: " +
                getResultsData(studentNumber, 1) +
                "\n" +
                "Request Status: " +
                getResultsData(studentNumber, 2) +
                "\n\n" +
                "Module Code , Semester , Academic year , Grade " +
                "\n\n" +
                getMultipleColumns(studentNumber, "1") + 
                "\n\n" +
                getMultipleColumns(studentNumber, "2") +
                "\n";
    }
}

