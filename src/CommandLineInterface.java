import java.util.Scanner;
public class CommandLineInterface {
    public void run() {
        Scanner scanner = new Scanner(System.in) ;
        Login login = new Login() ;
        QcaCalc qcaCalculator = new QcaCalc() ;
        Transcript transcript = new Transcript() ;

        boolean selectionPassed = false;
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

         System.out.println("Please enter your username (University Number) ");
         String username = scanner.nextLine();
         login.setUsername(username);

         System.out.println("Please enter your password ");
         String password = scanner.nextLine();

         if (login.correctLogin(username, password, login.getDivision())) {
             System.out.println("You have successfully logged in ! ") ;

         } else if (username.length() != 8 || password.length() != 6 ) {
             System.out.println("Please enter the correct length Username and password , Username is 8 characters and password is 6 characters");
         }
         else if (login.correctDivision(username, password, login.getDivision())) {
             //FIXME This doesnt work, Something to do with booleans
             System.out.println( "This username and password belongs to the " + login.getDivision() + " division, not the division you have chosen");
         }
         else {
             System.out.println("Please enter the correct username and password !");
         }

        String division =  login.getDivision();
        if (division.equals("Student")) {
            System.out.println("Enter your command (C)alculate QCA (V)iew Transcript (R)equest action" );
            String commandStudent = scanner.nextLine().toUpperCase();

            switch (commandStudent) {
                case "C" -> {

                    //TODO: Make this so we store grades for students instead of asking for their grades

                    System.out.println("Please enter this semesters grades in the format ,A1 ,A2,B1");
                    String gradesInput = scanner.nextLine();
                    String[] grades = gradesInput.split(",\\s*"); //FIXME: Needs work
                    System.out.println("Cummulative QCA is " + String.format("%.2f", qcaCalculator.getCummulativeQca(grades)) + " for the " + grades.length + " modules you have taken");
                }

                case "V" -> System.out.println(transcript.printTranscript(login.getUsername()));

                case "R" -> {
                    System.out.println("Enter your command, (R)equest repeat , (L)ink in module , (Y)ear repeat , (S)emester repeat");
                    String commandRequest = scanner.nextLine();
                    switch (commandRequest) {
                        case "R" -> {
                            System.out.println("Enter Module code for your requested module");
                            String commandModule = scanner.nextLine().toUpperCase(); //TODO: validate choice - 6 Digit length

                            // TODO: Check module is on transcript , if so add this to student transcript
                            // Add to student transcript method
                            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                            transcript.setRequest(" has requested to repeat module: " + commandModule);
                            transcript.recordRequest(); // Add to CSV file

                        }
                        case "L" -> {
                            System.out.println("Enter Module code for your requested Link-in module");
                            String commandModule = scanner.nextLine().toUpperCase(); //TODO: validate choice - 6 Digit length

                            transcript.setRequest("has requested to take module : " + commandModule + " as a link in module");
                            transcript.recordRequest(); // Add to CSV file

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
                            transcript.recordRequest(); // Add to CSV file
                            transcript.setRequest(" has requested to repeat AY : " + commandYear);
                        }
                        case "S" -> {
                            System.out.println("Enter the Semester and AY in which you wish to repeat");
                            String commandSemester = scanner.nextLine().toUpperCase();

                            //TODO: validate choice
                            //Check module is on transcript , if so add this to student transcript
                            // Add to student transcript method

                            transcript.setRequest(" has requested to repeat semester : " + commandSemester);
                            transcript.recordRequest(); // Add to CSV file
                            System.out.println("Your repeat request has being registered and is reflected on your transcript ");
                        }
                    }
                }
            }
        // TODO: Need logic to prevent passing students from requesting repeats
        }

        if (division.equals("Faculty")) {
        System.out.println("Please enter the student number to submit results" ); // Need to review the flowchart and specs
        String commandFaculty = scanner.nextLine().toUpperCase();
        }

        if (division.equals("Department")) {
            System.out.println("Please enter the student number to submit results" ); // Need to review the flowchart and specs
            String studentNumber = scanner.nextLine();
            if (!login.isStudent(studentNumber, "Student")) {
                System.out.println("Invalid Student number ! Please enter a correct one ") ;

            }
        }
    }
}
