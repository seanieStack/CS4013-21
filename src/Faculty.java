import java.util.*;

/**
 * Represents a Person that is a faculty member.
 */

public class Faculty extends Person {
    Scanner scanner;
    Transcript transcript;
    CsvReader reader;

    /**
     * Constructs a Faculty object with the provided username and password.
     *
     * @param username The username of the faculty member.
     * @param password The password of the faculty member.
     */
    
    public Faculty(String username, String password) {
        super(username, password);
        scanner = new Scanner(System.in);
        transcript = new Transcript();
        reader = new CsvReader();
    }

    /**
     * Handles the logic for faculty tasks, such as running the exam board,
     * viewing transcripts, identifying at risk students, and reviewing student progression.
     */

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
                        System.out.println(transcript.printTranscript(selectedStudentNumber)); 
                    }
                    case "I" -> {
                        // Handle Identify At-Risk Students
                        System.out.println("Identifying at-risk students...");
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
                              System.out.println("Student ("+entry.getKey() + ") has a QCA of : " + QcaCalc.getCummulativeQca(grades));
                            }
                        }
                    }
                    case "R" -> {
                        System.out.println("Reviewing student progression...(QCA under 2)");
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
                                transcript.setRequestResult(true, selectedStudentNumber); 
                                System.out.println("Your decision has being recorded and is now reflected on the students transcript , add your justification for this decision below");
                                String approveComments = scanner.nextLine();
                                transcript.setRequestComments(selectedStudentNumber, approveComments);
                            } else {
                                System.out.println("Invalid input");
                            }
                        }

                    }
                    case "B" -> backToFacultyMenu = true;
                    default -> System.out.println("Invalid Input! Please select a valid option");
                }
            }
        }
    }
}
