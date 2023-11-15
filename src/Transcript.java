import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public String getData(String studentNumber, int columnIndex) { //Change column index later to take String and not INT
        for (String[] row : reader.CsvSearch("data/LoginInfo.csv")) {
            String username = row[0];
    
            if (username.equals(studentNumber) && row.length > columnIndex) {
                return row[columnIndex]; // Return the data from the specified column
            }
        }
        return null; // or return an empty string if no match is found
    }

    public String abbreivation (String studentNumber) {
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

    public String formatRequest () {
        String username = login.getUsername() ; 
        String firstName = getData(username, 4);
        String secondName = getData(username, 5);
        return firstName + " " + secondName + " " + getRequest();
    }

    public void recordRequest() {
        String request = formatRequest();
        writer.modifySpecificRowInCsv("Data/StudentResults.csv", 1, "request");
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
                "Name "+ abbreivation(studentNumber)+"        "+getData(studentNumber, 4)+"        "+getData(studentNumber, 6) + "                                                  " + "\n" +
                "Address "+ getData(studentNumber, 6) +"                                                                                     " + "\n" +
                "        "+ getData(studentNumber, 7) +"                                                                                       " + "\n" +
                "        "+ getData(studentNumber,8) +"                                                       Telephone  "+ getData(studentNumber, 9) +"             " + "\n" +
                "                                                                                                  " + "\n" +
                "                                                                                                  " + "\n" +
                "Status    "+ getData(studentNumber, 10) +"                              Batch   (random number)                            " + "\n" +
                "Course    "+ getData(studentNumber, 10) +"                              Adivisor                                           " + "\n" +
                "Programme "+ getData(studentNumber, 10) +"                              Award                                              " + "\n" +
                "Route     "+ getData(studentNumber, 11) +"                                            Class                                              ";
                

            // FIXME: this shit fucked
             //   public String printTranscriptModules () { // Unkown error in this 

             //   return "+-----------------------------------------------------------------+------------------------------+" + "\n" +
             //   "| AY           SEMx     Part 1                                    |               Session To-Date|" + "\n" +
             //   "|                                                                 |                              |" + "\n" +
             //   "| Module      Title            Blockx    Regn type  Grade  Credits|Factor      1.00              |" + "\n" +
             //   "|                                                                 |Att hours  30.00        30.00 |" ;
            //
              //  }
              
    }



}
