import java.io.IOException;
import java.util.Scanner;
public class CommandLineInterface {
    public void run() {
        Scanner scanner = new Scanner(System.in) ;
        Login login = new Login() ;
        QcaCalc qcaCalculator = new QcaCalc() ;
        Transcript transcript = new Transcript() ;
        Faculty faculty = new Faculty();

        String username = "";
        String password = "";

        boolean selectionPassed = false; //Fix: This was working , now can continue even without sucsessful login , Too tired to fix now
        while(!selectionPassed) {
            System.out.println("Choose your division , (S)tudent , (F)aculty , (D)epartment");
            String command = scanner.nextLine().toUpperCase();
            switch (command) {
                case "S" -> {
                    login.setDivision("Student");
                    selectionPassed = true;
                }
                case "F" -> {
                    login.setDivision("Faculty");
                    selectionPassed = true;
                }
                case "D" -> {
                    login.setDivision("Department");
                    selectionPassed = true;
                }
                default -> System.out.println("Invalid command line Usage , Please select a valid option ");
            }
        }

         boolean loggedIn = false;
         while(!loggedIn) {

             System.out.println("Please enter your username (University Number) ");
             username = scanner.nextLine();
             login.setUsername(username);

             System.out.println("Please enter your password ");
             password = scanner.nextLine();

             if (login.correctLogin(username, password, login.getDivision())) {
                 System.out.println("You have successfully logged in !");
                 loggedIn = true;
             } else if (username.length() != 8 || password.length() != 6) {
                 System.out.println("Please enter the correct length Username and password , Username is 8 characters and password is 6 characters");
             } else {
                 System.out.println("Please enter the correct username and password !");
             }
         }
        String division =  login.getDivision();
        if (division.equals("Student")) {
            System.out.println("Enter your command (C)alculate QCA (V)iew Transcript (R)equest action" );
            String commandStudent = scanner.nextLine().toUpperCase();

            switch (commandStudent) {
                case "C" -> {

                    //TODO: Make this so we store grades for students instead of asking for their grades
                    //COMMENT: We do not need to store these grades, Students can not updated there grades.... Just like in real life 

                    System.out.println("Please enter this semesters grades in the format ,A1 ,A2,B1");
                    String gradesInput = scanner.nextLine();
                    String[] grades = gradesInput.split(",\\s*"); //FIXME: Needs work
                    System.out.println("Cummulative QCA is " + String.format("%.2f", qcaCalculator.getCummulativeQca(grades)) + " for the " + grades.length + " modules you have taken");
                }

                case "V" -> System.out.println(transcript.printTranscript(login.getUsername()));

                case "R" -> {
                    System.out.println("Enter your command, (R)equest repeat , (L)ink in module , (Y)ear repeat , (S)emester repeat");
                    String commandRequest = scanner.nextLine();
                    login.setUsername(username);
                    switch (commandRequest) {
                        case "R" -> {
                            System.out.println("Enter Module code for your requested module");
                            String commandModule = scanner.nextLine().toUpperCase(); //TODO: validate choice - 6 Digit length

                            // TODO: Check module is on transcript , if so add this to student transcript
                            // Add to student transcript method
                            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                            transcript.setRequest(" has requested to repeat module: " + commandModule);
                            transcript.recordRequest(username); // Add to CSV file

                        }
                        case "L" -> {
                            System.out.println("Enter Module code for your requested Link-in module");
                            String commandModule = scanner.nextLine().toUpperCase(); //TODO (DONE): validate choice - 6 Digit length, D
                            if (commandModule.length() != 6) {
                                System.out.println("Module code must have a 6 digit length !") ;
                            }

                            transcript.setRequest("has requested to take module : " + commandModule + " as a link in module");
                            transcript.recordRequest(username); // Add to CSV file

                            // Add to student transcript method
                            System.out.println("Your link in module request has being registered and is reflected on your transcript ");
                        }
                        case "Y" -> {
                            System.out.println("Enter the academic year in which you wish to repeat");
                            String commandYear = scanner.nextLine().toUpperCase();

                            //TODO: validate choice
                            //Check module is on transcript , if so add this to student transcript
                            // Add to student transcript method

                            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                            transcript.recordRequest(username); // Add to CSV file
                            transcript.setRequest(" has requested to repeat AY : " + commandYear);
                        }
                        case "S" -> {
                            System.out.println("Enter the Semester and AY in which you wish to repeat");
                            String commandSemester = scanner.nextLine().toUpperCase();

                            //TODO: validate choice
                            //Check module is on transcript , if so add this to student transcript
                            // Add to student transcript method

                            transcript.setRequest(" has requested to repeat semester : " + commandSemester);
                            transcript.recordRequest(username); // Add to CSV file
                            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                        }
                    }
                }
            }
        // TODO: Need logic to prevent passing students from requesting repeats
        // COMMENT: We will need Grades.CSV to be fully integrated first , Adding a credits (recieved or not) field to QcaCalc class
        }

        if (division.equals("Faculty")) {
            System.out.println("Press (R) to run exam board, Please note the next exam board meets on the 31/1/24");
            String commandRunBoard = scanner.nextLine().toUpperCase();
        
            if (commandRunBoard.equals("R")) {
                boolean backToFacultyMenu = false;
                while (!backToFacultyMenu) {
                System.out.println("Enter your command, (V)iew Transcript, (I)dentify at-risk students, (R)eview student progression");
                String facultyCommand = scanner.nextLine().toUpperCase();
        
                switch (facultyCommand) {
                    case "V" -> {
                        // Handle View Transcript
                        System.out.println("Please enter the student number of the students Transcript that you wish to view ");
                        String selectedStudentNumber = scanner.nextLine() ;
                        login.setUsername(selectedStudentNumber);
                        System.out.println(transcript.printTranscript(login.getUsername())); //TODO: Fix this stupid shit
                    }
                    case "I" -> {
                        // Handle Identify At-Risk Students
                        System.out.println("Identifying at-risk students...");
                        // Scan cummulative QCA from grades csv and print student number , users can view transcript from there 
                        // Also print all Students with a QCA < 2 in Semester 1 
                    }
                    case "R" -> {
                        // Handle Review Student Progression
                        //COMMENT: Don't think this needs any more changes ,Students should automatically pass if QCA >= 2, Maybe we should ask him for some clarification on this
                        System.out.println("Reviewing student progression...");
                        System.out.println("Please enter the student number of the student you wish to review");
                        String selectedStudentNumber = scanner.nextLine() ;
                        login.setUsername(selectedStudentNumber);;
                        if (transcript.getResultsData(selectedStudentNumber, 1 )!= null); {
                            System.out.println(transcript.getResultsData(selectedStudentNumber, 1));
                            System.out.println("--------------------------------------------------------------------");
                            System.out.println("Enter Y to approve , Enter N to deny ");
                            String commandApprove = scanner.nextLine().toUpperCase();
                            
                            if (commandApprove.equals("Y")) {
                                transcript.setRequestResult(true, selectedStudentNumber); 
                                

                            }
                            else if (commandApprove.equals("N")) {
                                transcript.setRequestResult(true, selectedStudentNumber);
                                System.out.println("Your decision has being recorded and is now reflected on the students transcript , add your justification for this decision below");
                                String aprroveComments = scanner.nextLine();
                                transcript.setRequestComments(selectedStudentNumber, aprroveComments);
                            } else {
                                System.out.println("Invalid input");
                            }
                            String aprroveComments = scanner.nextLine();
                        }
            
                    }
                    case "B" -> {
                        backToFacultyMenu = true; // Set flag to true to go back to the previous menu, 
                    }
                    default -> System.out.println("Invalid Input! Please select a valid option");
                }
            }
        }
              else {
                System.out.println("Invalid Input! Please select a valid option");
            }
        }
    
       // System.out.println("Please enter the student number to submit results" ); // Need to review the flowchart and specs
       // String commandFaculty = scanner.nextLine().toUpperCase();
        





        if (division.equals("Department")) { // TODO: Stop users logging in without correct info 
            System.out.print("Use command (S) to (S)ubmit results");
            String commandSubmit = scanner.nextLine().toUpperCase();
            if (commandSubmit.equals("S")) {
                System.out.println("Enter Module code");
                String moduleCode = scanner.nextLine().toUpperCase() ;
                if (moduleCode.length() != 6) {
                    System.out.println("Module code are 6 digits in length ! please renenter");
                }
                if ( faculty.searchModules(moduleCode) == false) { //TODO : Use ! instead
                    System.out.println("Module not found");
                 }
                    System.out.println("Enter student Number");
                    String selectedStudentNumber = scanner.nextLine();
                    if (!login.isStudent(selectedStudentNumber, "Student")|| selectedStudentNumber.length() != 6) {
                        System.out.println("Invalid Student number ! Please enter a correct one ") ;
                    }
                    System.out.println("Enter Grade e.g A1");
                    String grade = scanner.nextLine();
                    // Verify is valid
                    // Update CSV
                   
                }
                
                // if so 
                //err.message = "This is not a module offered by UL , try again"
            } else {
                System.out.println("Invalid Input !");
            }
            
            
            
            System.out.println("Please enter the student number to submit results" ); // Need to review the flowchart and specs
            String studentNumber = scanner.nextLine();
            if (!login.isStudent(studentNumber, "Student")) {
                System.out.println("Invalid Student number ! Please enter a correct one ") ;

            }

        }
    }


