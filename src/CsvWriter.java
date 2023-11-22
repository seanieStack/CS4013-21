import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.util.Arrays;



public class CsvWriter {

    // Constructor that reads the CSV data
    public void modifySpecificRowInCsv(String csvFilePath, String username, String strColumnIndex, String newData) {
    List<String> fileContent = new ArrayList<>();
    boolean rowFound = false;
    int columnIndex =  Integer.parseInt(strColumnIndex);

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(username + ",")) {
                String[] rowData = line.split(",");
                if (columnIndex < rowData.length) {
                    rowData[columnIndex] = newData; // Update the data at specified column
                } else {
                    // Extend the row with empty columns up to the required index
                    rowData = Arrays.copyOf(rowData, columnIndex + 1);
                    rowData[columnIndex] = newData;
                }
                line = String.join(",", rowData);
                rowFound = true;
            }
            fileContent.add(line);
        }

        if (!rowFound) {
            // Create a new row with empty columns up to the required index
            String[] newRowData = new String[columnIndex + 1];
            Arrays.fill(newRowData, "");
            newRowData[0] = username;
            newRowData[columnIndex] = newData;
            line = String.join(",", newRowData);
            fileContent.add(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Write updated content back to file
    try (FileWriter fw = new FileWriter(csvFilePath, false)) { // 'false' to overwrite
        for (String fileLine : fileContent) {
            fw.append(fileLine).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void appendOrUpdateRow(String csvFilePath, String username, String newData) {
    List<String> fileContent = new ArrayList<>();
    boolean rowUpdated = false;

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(username + ",")) {
                // Update the existing row
                fileContent.add(newData);
                rowUpdated = true;
            } else {
                fileContent.add(line);
            }
        }

        if (!rowUpdated) {
            // Append the new row
            fileContent.add(newData);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Write updated content back to file
    try (FileWriter fw = new FileWriter(csvFilePath, false)) { // 'false' to overwrite
        for (String fileLine : fileContent) {
            fw.append(fileLine).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }



}
}
