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
                if (columnIndex >= rowData.length) {
                    // Extend the row with empty columns up to the required index
                    rowData = Arrays.copyOf(rowData, columnIndex + 1);
                }
                rowData[columnIndex] = newData; // Update the data at specified column
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

public void appendOrUpdateRow(String csvFilePath, String username, String moduleCode, String semester, String newData) {
    List<String> fileContent = new ArrayList<>();
    boolean rowFound = false;

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length > 2 && parts[0].equals(username) && parts[1].equals(moduleCode) && parts[2].equals(semester)) {
                // Update the existing row if username, moduleCode, and semester match
                fileContent.add(newData);
                rowFound = true;
            } else {
                // Add the line as is if it's not the one we want to update
                fileContent.add(line);
            }
        }

        if (!rowFound) {
            // Append the new row if no matching row was found
            fileContent.add(newData);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Write updated content back to the file
    try (FileWriter fw = new FileWriter(csvFilePath, false)) { // 'false' to overwrite
        for (String fileLine : fileContent) {
            fw.append(fileLine).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
