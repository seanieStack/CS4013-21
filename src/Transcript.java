import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    * Retrieves a list of grades for a specific student, semester, and academic year.
    * 
    * @param username The student number to match in the CSV data.
    * @param semester The semester to filter the grades.
    * @param ay The academic year to filter the grades.
    * @return A list of grades (as strings) for the specified student, semester, and academic year.
    */

     public List<String> getSemesterGrades( String username, String semester,String ay) {
        // Create an instance of csvReader to use its CsvSearch method
        List<String[]> csvData = reader.CsvSearch("CS4013-21/src/data/StudentGrades.csv");
        List<String> grades = new ArrayList<>();

        for (String[] row : csvData) {
            // Check if the row matches the username and semester
            if (row[0].equals(username) && row[2].equals(semester)&& row[3].equals(ay)) {
                grades.add(row[4]); // Add the grade to the list
            }
        }

        return grades;
    }

    /**
    * Calculates the  (QCA) for a student for a given semester and academic year.
    * This method first retrieves the grades for the specified criteria and then computes the QCA,
    * rounding it to two decimal points.
    * 
    * @param studentNumber The student number for which the QCA is to be calculated.
    * @param semester The semester for which the QCA is to be calculated.
    * @param ay The academic year for which the QCA is to be calculated.
    * @return The calculated QCA, rounded to two decimal points.
    */
    
    public double calculateQcaForStudent( String studentNumber, String semester,String ay) {
        List<String> grades = getSemesterGrades( studentNumber, semester,ay);
        String[] gradesArray = grades.toArray(new String[0]); // Convert List to Array
        double qca = QcaCalc.getCummulativeQca(gradesArray);
        return Double.parseDouble(String.format("%.2f", qca));
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
                "\n\n" +
                "Semester 1 QCA : " + calculateQcaForStudent( studentNumber, "1","2023/2024") +
                "\n\n" +
                getMultipleColumns(studentNumber, "2") +
                "\n\n" +
                "Semester 2 QCA : " + calculateQcaForStudent( studentNumber, "2","2023/2024") +
                "\n\n" +
                "Cummulative QCA for Student ()" + studentNumber +") :" + (calculateQcaForStudent( studentNumber, "1","2023/2024") + calculateQcaForStudent( studentNumber, "2","2023/2024") /2 ) +
                "\n\n" ;
    }
}

