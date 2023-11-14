import java.util.HashMap;
import java.util.Map;

public class QcaCalc {
    public static  Map<String, Double> gradeQcaMap = new HashMap<>();

    static {
        gradeQcaMap.put("A1",4.00);
        gradeQcaMap.put("A2",3.6);
        gradeQcaMap.put("B1",3.2);
        gradeQcaMap.put("B2",3.00);
        gradeQcaMap.put("B3",2.8);
        gradeQcaMap.put("C1",2.6);
        gradeQcaMap.put("C2",2.4);
        gradeQcaMap.put("C3",2.0);
        gradeQcaMap.put("D1",1.6);
        gradeQcaMap.put("D2",1.2);
        gradeQcaMap.put("F",0.0);
        gradeQcaMap.put("NG",0.0);
        gradeQcaMap.put("G",0.0);
        gradeQcaMap.put("I",0.0); // Integrate -1 with grades.length
        gradeQcaMap.put("M",0.0);
        gradeQcaMap.put("P",0.0);
        gradeQcaMap.put("N",0.0);

        // Add G,I,M,P and N grades at later stage
        // Needs some logic for if credits awarded or not ...
    }



    public double getCummulativeQca ( String ... grades) { // I used to Varargs here instead of method overloading for more clean and readable code
        int numModules = grades.length;
        double cummulativeQca = 0.00;

        for (String grade : grades) {
            cummulativeQca += (gradeQcaMap.get(grade)) / numModules;

        }
        return cummulativeQca;
    }
}
