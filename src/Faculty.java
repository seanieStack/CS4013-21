import java.util.*;

public class Faculty extends Person {
    Scanner scanner;
    Transcript transcript;
    CsvReader reader;

    public Faculty(String username, String password) {
        super(username, password);
        scanner = new Scanner(System.in);
        transcript = new Transcript();
        reader = new CsvReader();
    }

    public void facultyLogic(){
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
                        System.out.println(transcript.printTranscript(selectedStudentNumber)); //TODO: Fix this stupid shit
                    }
                    case "I" -> {
                        // Handle Identify At-Risk Students
                        System.out.println("Identifying at-risk students...");
                        // Scan cummulative QCA from grades csv and print student number , users can view transcript from there
                        // Also print all Students with a QCA < 2 in Semester 1

                        List<String[]> csvData = reader.CsvSearch("./src/data/StudentGrades.csv");
                        Map<Integer, List<String>> studentGrades = new HashMap<>();
                        for (int i = 1; i < csvData.size(); i++) {
                            if(csvData.get(i).length == 5) {
                                int studentNumber = Integer.parseInt(csvData.get(i)[0]);
                                String studentGrade = csvData.get(i)[4];
                                studentGrades.computeIfAbsent(studentNumber, k -> new ArrayList<>()).add(studentGrade);
                            }
                        }
                        for(Map.Entry<Integer, List<String>> entry : studentGrades.entrySet()){
                            String[] grades = entry.getValue().toArray(new String[0]);
                            if(QcaCalc.getCummulativeQca(grades) <= 2.0){
                                System.out.println(entry.getKey() + ":" + QcaCalc.getCummulativeQca(grades));
                            }
                        }
                    }
                    case "R" -> {
                        // Handle Review Student Progression
                        //COMMENT: Don't think this needs any more changes ,Students should automatically pass if QCA >= 2, Maybe we should ask him for some clarification on this
                        System.out.println("Reviewing student progression...");
                        System.out.println("Please enter the student number of the student you wish to review");
                        String selectedStudentNumber = scanner.nextLine() ;
                        if (transcript.getResultsData(selectedStudentNumber, 1 )!= null) {
                            System.out.println(transcript.getResultsData(selectedStudentNumber, 1));
                            System.out.println("--------------------------------------------------------------------");
                            System.out.println("Enter Y to approve , Enter N to deny ");
                            String commandApprove = scanner.nextLine().toUpperCase();

                            if (commandApprove.equals("Y")) {
                                transcript.setRequestResult(true, selectedStudentNumber);


                            }
                            else if (commandApprove.equals("N")) {
                                transcript.setRequestResult(true, selectedStudentNumber); //TODO: Change to false , N = Deny , test and review for push
                                System.out.println("Your decision has being recorded and is now reflected on the students transcript , add your justification for this decision below");
                                String approveComments = scanner.nextLine();
                                transcript.setRequestComments(selectedStudentNumber, approveComments);
                            } else {
                                System.out.println("Invalid input");
                            }
                        }

                    }
                    case "B" -> backToFacultyMenu = true; // Set flag to true to go back to the previous menu,
                    default -> System.out.println("Invalid Input! Please select a valid option");
                }
            }
        }
    }
}
