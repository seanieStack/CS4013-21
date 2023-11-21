import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transcript {
    // This class deals with all the formatting of the student Transcript 
    private String action ;

    CsvReader reader = new CsvReader();
    CsvWriter writer = new CsvWriter();
    Login login = new Login();

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
        for (String[] row : reader.CsvSearch("CS4013-21/src/data/LoginInfo.csv")) {
            String username = row[0];
            if (username.equals(studentNumber) && row.length > columnIndex) {
                return row[columnIndex]; // Return the data from the specified column
            }
        }
        return ""; // Return an empty string instead of null
    }

    public String getResultsData(String studentNumber, int columnIndex) { //TODO: Merge these 2 methods , Quick fix just to get the system built
        for (String[] row : reader.CsvSearch("CS4013-21/src/data/StudentResults.csv")) {
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
        writer.modifySpecificRowInCsv("CS4013-21/src/data/StudentResults.csv", username,"1", request);
    }
    public void setRequestResult(boolean approved,String username) {
        if (approved) {
            writer.modifySpecificRowInCsv("CS4013-21/src/data/StudentResults.csv", username,"2", "Approved");
        } else {
            writer.modifySpecificRowInCsv("CS4013-21/src/data/StudentResults.csv", username,"2" ,"Denied");
        }
    }

    public void setRequestComments(String username , String comments ){
        writer.modifySpecificRowInCsv("CS4013-21/src/data/StudentResults.csv", username,"3", comments);
    }
    

    public String printTranscript (String studentNumber) {
        // TODO: Edit to take String username as parameter , this will be used as the key to search
        // TODO: maybe make this more readable
        String headerText  = ("\033[1m Student Transcript \033[0m");
        String outputHeaderText = printCentered(headerText, 98);
        StringBuilder transcript = new StringBuilder();
        transcript.append(outputHeaderText)
                .append("\n")
                .append("University of Limerick\n")
                .append(currentDate())
                .append("  Full Student Transcript                      ")
                .append(studentNumber)
                .append("\n")
                .append("\n")
                .append("Name: ")
                .append(abbreviation(studentNumber))
                .append("\n")
                .append("Address: ")
                .append(getData(studentNumber, 6))
                .append("\n")
                .append("        ")
                .append(getData(studentNumber, 7))
                .append("\n")
                .append("        ")
                .append(getData(studentNumber, 8))
                .append("  Telephone: ")
                .append(getData(studentNumber, 9))
                .append("\n\n")
                .append("Status: ")
                .append(getData(studentNumber, 10))
                .append("                      Batch: (random number)\n")
                .append("Course: ")
                .append(getData(studentNumber, 10))
                .append("                      Advisor: ")
                .append("\n")
                .append("Programme: ")
                .append(getData(studentNumber, 10))
                .append("                      Award: ")
                .append("\n")
                .append("Route: ")
                .append(getData(studentNumber, 11))
                .append("                                    Class: ")
                .append("\n")
                .append("Request: ")
                .append(getResultsData(studentNumber, 1))
                .append("\n")
                .append("Request Status: ")
                .append(getResultsData(studentNumber, 2))
                .append("\n");

        return transcript.toString();

            // FIXME: this shit fucked 
            // COMMENT: it is fucked , but we ever get far enough to include this we will probably need this code
             //   public String printTranscriptModules () { // Unknown error in this

             //   return "+-----------------------------------------------------------------+------------------------------+" + "\n" +
             //   "| AY           SEMx     Part 1                                    |               Session To-Date|" + "\n" +
             //   "|                                                                 |                              |" + "\n" +
             //   "| Module      Title            Blockx    Regn type  Grade  Credits|Factor      1.00              |" + "\n" +
             //   "|                                                                 |Att hours  30.00        30.00 |" ;
            //
              //  }
              
    }



}
