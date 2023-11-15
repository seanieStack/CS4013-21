import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;


public class CsvWriter {

    // Constructor that reads the CSV data
    public void modifySpecificRowInCsv(String csvFilePath, int rowToModify, String newData) {
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentLine = 1;
            while ((line = br.readLine()) != null) {
                if (currentLine == rowToModify) {
                    fileContent.add(newData);
                } else {
                    fileContent.add(line);
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fw = new FileWriter(csvFilePath, false)) { // 'false' to overwrite
            for (String line : fileContent) {
                fw.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
