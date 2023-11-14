import main.java.ulsrs.core.people.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The CsvWriter class provides methods to write collections of different person types to CSV files.
 */
public class CsvWriter {

    /**
     * Writes a collection of students to a CSV file.
     *
     * @param students The ArrayList of Student objects to be written to the CSV file.
     */
    public static void writeStudentsToCsv(ArrayList<Student> students){
        try (FileWriter writer = new FileWriter("students.csv")){
            String[] properties = {"firstName","lastName","age","address","studentId","course","QCA"};
            writer.append(String.join(",", properties));
            writer.append("\n");

            for(Student student : students){
                writer.append(student.returnRelevantInfo());
                writer.append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes a collection of faculty members to a CSV file.
     *
     * @param facultyMembers The ArrayList of Faculty objects to be written to the CSV file.
     */
    public static void writeFacultyToCsv(ArrayList<Faculty> facultyMembers){
        try (FileWriter writer = new FileWriter("faculties.csv")){
            String[] properties = {"firstName","lastName","age","address","employeeId","department","title"};
            writer.append(String.join(",", properties));
            writer.append("\n");

            for(Faculty faculty : facultyMembers){
                writer.append(faculty.returnRelevantInfo());
                writer.append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes a collection of department members to a CSV file.
     *
     * @param departmentMembers The ArrayList of Department objects to be written to the CSV file.
     */
    public static void writeDepartmentToCsv(ArrayList<Department> departmentMembers){
        try(FileWriter writer = new FileWriter("departments.csv")) {
            String[] properties = {"firstName","lastName","age","address","employeeId","department","title"};
            writer.append(String.join(",", properties));
            writer.append("\n");

            for(Department departmentMember : departmentMembers){
                writer.append(departmentMember.returnRelevantInfo());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}