import java.util.Scanner;

    /**Extends the Person class and allows student to view their transcript, their QCA and request actions 
    */

public class Student extends Person{
    Scanner scanner;
    Transcript transcript;

    /**Constructor for the Student class
    *@param username -the students username
    *@param password -the students password
    */
    
    public Student(String username, String password){
        super(username, password);
        scanner = new Scanner(System.in);
        transcript = new Transcript();
    }

    /**Method containing all student logic interactions
    *Allows students to calculate, view transcript or make requests
    */

    public void studentLogic(){
        //Gives the options to the studen
        System.out.println("Enter your command (C)alculate QCA (V)iew Transcript (R)equest action" );
        String commandStudent = scanner.nextLine().toUpperCase();

        switch (commandStudent) {
            case "C" -> {
                //Handles the QCA calculation
                System.out.println("Please enter this semesters grades in the format A1,A2,B1");
                String gradesInput = scanner.nextLine();
                String[] grades = gradesInput.split(",\\s*"); 
                System.out.println("Cummulative QCA is " + String.format("%.2f", QcaCalc.getCummulativeQca(grades)) + " for the " + grades.length + " modules you have taken");
            }
            
            //Handles the viewing of the transcript
            case "V" -> System.out.println(transcript.printTranscript(username));

            //Handles all student requests
            case "R" -> {
                System.out.println("Enter your command, (R)equest repeat , (L)ink in module , (Y)ear repeat , (S)emester repeat");
                String commandRequest = scanner.nextLine().toUpperCase();
                switch (commandRequest) {
                    case "R" -> {
                        System.out.println("Enter Module code for your requested module");
                        String commandModule = scanner.nextLine().toUpperCase(); //TODO: validate choice - 6 Digit length

                        System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                        transcript.setRequest(" has requested to repeat module: " + commandModule);
                        transcript.recordRequest(username); // Add to CSV file

                    }
                    case "L" -> {
                        System.out.println("Enter Module code for your requested Link-in module");
                        String commandModule = scanner.nextLine().toUpperCase(); 
                        if (commandModule.length() != 6) {
                            System.out.println("Module code must have a 6 digit length !") ;
                        }

                        transcript.setRequest("has requested to take module : " + commandModule + " as a link in module");
                        transcript.recordRequest(username); // Add to CSV file

                    
                        System.out.println("Your link in module request has being registered and is reflected on your transcript ");
                    }
                    case "Y" -> {
                        System.out.println("Enter the academic year in which you wish to repeat");
                        String commandYear = scanner.nextLine().toUpperCase();

                     

                        System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                        transcript.recordRequest(username); // Add to CSV file
                        transcript.setRequest(" has requested to repeat AY : " + commandYear);
                    }
                    case "S" -> {
                        System.out.println("Enter the Semester and AY in which you wish to repeat");
                        String commandSemester = scanner.nextLine().toUpperCase();


                        transcript.setRequest(" has requested to repeat semester : " + commandSemester);
                        transcript.recordRequest(username); // Add to CSV file
                        System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                    }
                }
            }
        }

        
        // COMMENT: We will need Grades.CSV to be fully integrated first , Adding a credits (received or not) field to QcaCalc class
    }
}
