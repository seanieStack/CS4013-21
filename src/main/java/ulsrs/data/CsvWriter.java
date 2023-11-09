package main.java.ulsrs.data;
import main.java.ulsrs.core.people.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvWriter {
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

    public static void writeDepartmentToCsv(ArrayList<Department> departmentMembers){
        try(FileWriter writer = new FileWriter("faculties.csv")) {
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
