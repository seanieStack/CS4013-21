import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Transcript {
    // This class deals with all the formatting of the student Transcript 
    private String action ;

    CsvReader reader = new CsvReader();
    CsvWriter writer = new CsvWriter();

    public String printCentered(String text, int width) {
        int padSize = width - text.length();
        int padStart = text.length() + padSize / 2;
    
        text = String.format("%" + padStart + "s", text);
        text = String.format("%-" + width  + "s", text);
        
        return text;
    }

    public String currentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return today.format(formatter);

    }

    //Row ordering 3 = Gender, 4 = Firstname , 5 = Lastname , 6= Address 1 ,7 = Address 2 , 8 = Address 3 , 9 = Telephone ,10 = course , 11 = route 

    public String getData(String studentNumber, int columnIndex) {
        for (String[] row : reader.CsvSearch("./src/data/LoginInfo.csv")) {
            String username = row[0];
            if (username.equals(studentNumber) && row.length > columnIndex) {
                return row[columnIndex]; // Return the data from the specified column
            }
        }
        return ""; // Return an empty string instead of null
    }

    public String getResultsData(String studentNumber, int columnIndex) {
        for (String[] row : reader.CsvSearch("./src/data/StudentResults.csv")) {
            String username = row[0];
            if (username.equals(studentNumber) && row.length > columnIndex) {
                return row[columnIndex]; // Return the data from the specified column
            }
        }
        return ""; // Return an empty string instead of null
    }

    public String abbreviation(String studentNumber) {
        return getData(studentNumber,3).equals("Male") ? "MR" : "MS";
    }

    public void setRequest (String action ) { // To be added to printTranscript method
         this.action = action;
    }

    public String getRequest () {
        return action;
    }

    public String formatRequest() {      
        return  "Student" + getRequest();
    }
    

    public void recordRequest(String username) {
        String request = formatRequest();
        writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"1", request);
    }
    public void setRequestResult(boolean approved,String username) {
        if (approved) {
            writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"2", "Approved");
        } else {
            writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"2" ,"Denied");
        }
    }

    public void setRequestComments(String username , String comments ){
        writer.modifySpecificRowInCsv("./src/data/StudentResults.csv", username,"3", comments);
    }

    public String getMultipleColumns(String  username, String semester ) {
        List<String[]> csvData = reader.CsvSearch("./src/data/StudentGrades.csv");

        return csvData.stream()
            .filter( row -> row.length > 4 && row[0].equals(username) && row[2].equals(semester)  )
            .map(row -> String.join("         ", row[1], row[2], row[3], row[4]))
            .collect(Collectors.joining("\n"));
    }
    

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
                getMultipleColumns(studentNumber, "1") + //COMMENT: Unsure why this isn't working ?,
                "\n\n" +
                getMultipleColumns(studentNumber, "2") +
                "\n";
    }
}

