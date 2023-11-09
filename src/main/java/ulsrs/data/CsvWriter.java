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
                basicPersonWriter(writer, student.getFirstName(), student.getLastName(), student.getAge(), student.getAddress());
                writer.append(student.getStudentId());
                writer.append(",");
                writer.append(student.getCourse());
                writer.append(",");
                writer.append(String.valueOf(student.getQCA()));
                writer.append(",");
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
                basicPersonWriter(writer, faculty.getFirstName(), faculty.getLastName(), faculty.getAge(), faculty.getAddress());
                writer.append(faculty.getEmployeeId());
                writer.append(",");
                writer.append(faculty.getDepartment());
                writer.append(",");
                writer.append(faculty.getTitle());
                writer.append(",");
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
                basicPersonWriter(writer, departmentMember.getFirstName(), departmentMember.getLastName(), departmentMember.getAge(), departmentMember.getAddress());
                writer.append(departmentMember.getDepartmentId());
                writer.append(",");
                writer.append(departmentMember.getDepartment());
                writer.append(",");
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void basicPersonWriter(FileWriter writer, String firstName, String lastName, int age, String address) throws IOException {
        writer.append(firstName);
        writer.append(",");
        writer.append(lastName);
        writer.append(",");
        writer.append(String.valueOf(age));
        writer.append(",");
        writer.append(address);
        writer.append(",");
    }
}
