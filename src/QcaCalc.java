import java.util.Map;

    /** A class which provides methods for calculating QCA based on grades.
    * It maps grades to the corresponding QCA.
    * Class gets its information from CSV file
    */

public class QcaCalc {
    //Creates object of the CsvReader class
    static CsvReader reader = new CsvReader();
    //Maps grades to corresponding QCA; reads grades from CSV file
    static Map<String, Double> gradeQcaMap = reader.readGrade("./src/data/grades.csv");

    /**Calculates QCA for a given set of grades
    *Takes a number of grades as strings and calculates QCA based on the mapped values
    *@param grades -array of the grades to be calculated
    *@return The QCA
     */
    public static double getCummulativeQca ( String ... grades) {
        //We used to Varargs here instead of method overloading for more clean and readable code
        int numModules = grades.length;
        //Initializes QCA to 0
        double cummulativeQca = 0.00;

        for (String grade : grades) {
            cummulativeQca += (gradeQcaMap.get(grade)) / numModules;

        }
        return cummulativeQca;
    }
}
