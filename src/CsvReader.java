
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class that reads data from CSV's.
 */

public class CsvReader {
      /**
     * Reads CSV data from the provided file path.
     *
     * @param csvFilePath The path of the CSV file.
     * @return A list of String arrays representing the data read from the CSV.
     */
    public List<String[]> CsvSearch (String csvFilePath) {

        final List<String[]> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csvData.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData ; 
    }
     /**
     * Reads grades data from the provided file path.
     *
     * @param fileName The path of the file containing the grades.
     * @return A map with student usernames as keys and their grades as values.
     */
    public Map<String, Double> readGrade(String fileName) {
        Map<String, Double> grades = new HashMap<>();
        boolean firstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                grades.put(parts[0], Double.valueOf(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grades;
    }
}
