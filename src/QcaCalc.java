import java.util.Map;

public class QcaCalc {

    static CsvReader reader = new CsvReader();
    static Map<String, Double> gradeQcaMap = reader.readGrade("./src/data/grades.csv");

    public static double getCummulativeQca ( String ... grades) {
        //I used to Varargs here instead of method overloading for more clean and readable code
        int numModules = grades.length;
        double cummulativeQca = 0.00;

        for (String grade : grades) {
            cummulativeQca += (gradeQcaMap.get(grade)) / numModules;

        }
        return cummulativeQca;
    }
}
