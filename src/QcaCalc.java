import java.util.HashMap;
import java.util.Map;

public class QcaCalc {
    static Map<String, Double> gradeQcaMap;

    static {
        CsvReader reader = new CsvReader();
        gradeQcaMap = reader.readGrade("data/grades.csv");
        reader = null;
    }


    public double getCummulativeQca ( String ... grades) {
        //I used to Varargs here instead of method overloading for more clean and readable code
        int numModules = grades.length;
        double cummulativeQca = 0.00;

        for (String grade : grades) {
            cummulativeQca += (gradeQcaMap.get(grade)) / numModules;

        }
        return cummulativeQca;
    }
}
