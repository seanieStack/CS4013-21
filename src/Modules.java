import java.util.List;

/**
 * This is a set of utility methods related to modules and student results.
 */

public class Modules {
    static CsvReader reader = new CsvReader();
    static CsvWriter writer = new CsvWriter();

      /**
     * Searches for a module with the provided module code.
     *
     * @param moduleCode The module code to search for.
     * @return True if the module is found, false otherwise.
     */

    public static boolean searchModules(String moduleCode) {
        List<String[]> csvData = reader.CsvSearch("./src/data/Modules.csv");

        // Skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            if (row.length > 1 && row[1].trim().equalsIgnoreCase(moduleCode.trim())) {
                return true; // Module found
            }
        }

        return false; // If module not found
    }

    /**
     * Checks if results exist for the provided student number and module code.
     *
     * @param studentNumber The student number.
     * @param moduleCode    The module code.
     * @return True if results exist, false otherwise.
     */
    
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

    /**
     * Checks if the provided grade is valid.
     *
     * @param grade The grade to validate.
     * @return True if the grade is valid, false otherwise.
     */
    
    public static boolean validGrade(String grade) {
        return QcaCalc.gradeQcaMap.containsKey(grade);
    }

        /**
     * Retrieves the semester info for a provided module code from the Modules CSV.
     *
     * @param moduleCode The module code to search for.
     * @return The semester of the module if it is found, or "Not Found" if the module is not found.
     */ 
    
      public static String getModuleSemester(String moduleCode) {
        List<String[]> csvData = reader.CsvSearch("CS4013-21/src/data/Modules.csv");
    
        // Skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            if (row.length > 3 && row[1].trim().equalsIgnoreCase(moduleCode.trim())) {
                return row[3].trim(); // Return the semester
            }
        }
    
        return "Not Found"; // If module not found
    }

    /**
     * Submits student results to the CSV.
     *
     * @param username   The student username.
     * @param moduleCode The module code.
     * @param ay         The academic year.
     * @param grade      The student's grade.
     */

    public static void submitResults(String username, String moduleCode, String semester, String ay, String grade) {
        String newData = String.join(",", username, moduleCode, semester, ay, grade); 
    
        
        writer.appendOrUpdateRow("CS4013-21/src/data/StudentGrades.csv", username, moduleCode, semester, newData);
    }
    

}
