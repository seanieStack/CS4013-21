import java.util.List;

public class Faculty { 
    CsvReader reader = new CsvReader();
    CsvWriter writer = new CsvWriter() ;

    
    public boolean searchModules(String moduleCode) {
        List<String[]> csvData = reader.CsvSearch("CS4013-21/src/data/Modules.csv");
    
        // Skip the header row
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            if (row.length > 1 && row[1].trim().equalsIgnoreCase(moduleCode.trim())) {
                return true; // Module found
            }
        }
    
        return false; // Module not found
    }
    
    //method to update CSV (grades.csv)
    
}