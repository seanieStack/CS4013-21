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

    public static void submitResults (String username, String moduleCode, String ay, String grade) {
        String newData = String.join(",", username, moduleCode, "1", ay ,grade); //Semester is set to 1 because it is current sem

        // This will append or update the whole row
        writer.appendOrUpdateRow("./src/data/StudentGrades.csv", username, newData);


    }
}