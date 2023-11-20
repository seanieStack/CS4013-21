
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {
    // TODO: e.printStackTrace() is generally not the best way to log error

    public List<String[]> CsvSearch (String csvFilePath) {
        // FIXME: This reads in the header 

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

    public Map<String, Double> readGrade(String fileName){
        Map<String, Double> grades = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                grades.put(line.split(",")[0], Double.valueOf(line.split(",")[1]));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return grades;
    }
}
