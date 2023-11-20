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
        if (getData(studentNumber,3).equals("Male")) {
            return "MR" ;
        }
        else {
            return "MS";
        }
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
        if (approved == true ) {
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

        return  outputHeaderText + "\n" +
                "+------------------------------------------------------------------------------------------------+" + "\n" +
                "|             University of Limerick                                                             |" + "\n" +
                "|"+currentDate()+"  Full Student Transcript                                       "+ studentNumber +"             |" + "\n" +
                "|                                                                                                |" + "\n" +
                "+------------------------------------------------------------------------------------------------+" + "\n" +
                "Name "+ abbreviation(studentNumber)+"        "+getData(studentNumber, 4)+"        "+getData(studentNumber, 6) + "                                                  " + "\n" +
                "Address "+ getData(studentNumber, 6) +"                                                                                     " + "\n" +
                "        "+ getData(studentNumber, 7) +"                                                                                       " + "\n" +
                "        "+ getData(studentNumber,8) +"                                                       Telephone  "+ getData(studentNumber, 9) +"             " + "\n" +
                "                                                                                                  " + "\n" +
                "                                                                                                  " + "\n" +
                "Status    "+ getData(studentNumber, 10) +"                              Batch   (random number)                            " + "\n" +
                "Course    "+ getData(studentNumber, 10) +"                              Advisor                                           " + "\n" +
                "Programme "+ getData(studentNumber, 10) +"                              Award                                              " + "\n" +
                "Route     "+ getData(studentNumber, 11) +"                                            Class                                              " + "\n" +
                "Request : " + getResultsData(studentNumber, 1) +"                                                                                 " + "\n" +
                "Reuest Status : "+ getResultsData(studentNumber, 2)+"                                                                             "; 
                

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
