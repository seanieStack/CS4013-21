import java.util.List;

public class Modules {
    static CsvReader reader = new CsvReader();
    static CsvWriter writer = new CsvWriter();

    public static boolean searchModules(String moduleCode) {
        List<String[]> csvData = reader.CsvSearch("./src/data/Modules.csv");

        // Skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            if (row.length > 1 && row[1].trim().equalsIgnoreCase(moduleCode.trim())) {
                return true; // Module found
            }
        }

        return false; // Module not found
    }

    public static boolean resultsExists(String studentNumber, String moduleCode) {
        List<String[]> csvData = reader.CsvSearch("./src/data/StudentResults.csv");

        for (String[] row : csvData) {
            // Assuming the first column is StudentNumber and the second is ModuleCode
            if (row.length > 1 && row[0].equals(studentNumber) && row[1].equals(moduleCode)) {
                return true; // Both student number and module code exist in the same row
            }
        }
        return false; // No matching row found
    }

    public static boolean validGrade(String grade) {
        return QcaCalc.gradeQcaMap.containsKey(grade);
    }

      public static String getModuleSemester(String moduleCode) {
        List<String[]> csvData = reader.CsvSearch("CS4013-21/src/data/Modules.csv");
    
        // Skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            if (row.length > 3 && row[1].trim().equalsIgnoreCase(moduleCode.trim())) {
                return row[3].trim(); // Return the semester
            }
        }
    
        return "Not Found"; // Module not found
    }



    public static void submitResults(String username, String moduleCode, String semester, String ay, String grade) {
        String newData = String.join(",", username, moduleCode, semester, ay, grade); 
    
        
        writer.appendOrUpdateRow("CS4013-21/src/data/StudentGrades.csv", username, moduleCode, semester, newData);
    }
    

}
