package std;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số lượng học viên
        System.out.print("Nhập số lượng học viên: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng trống

        // Nhập thông tin của từng học viên
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin học viên thứ " + (i + 1) + ":");
            System.out.print("Mã sinh viên: ");
            String id = scanner.nextLine();
            System.out.print("Họ và tên: ");
            String fullName = scanner.nextLine();
            System.out.print("Giới tính: ");
            String gender = scanner.nextLine();
            System.out.print("Điểm Python: ");
            double pythonScore = scanner.nextDouble();
            System.out.print("Điểm Java: ");
            double javaScore = scanner.nextDouble();
            scanner.nextLine(); // Đọc bỏ dòng trống

            students.add(new Student(id, fullName, gender, pythonScore, javaScore));
        }

        // Ghi danh sách học viên ra file
        try (PrintWriter writer = new PrintWriter("students.txt")) {
            for (Student student : students) {
                writer.println(student.id + "," + student.fullName + "," + student.gender + "," +
                               student.pythonScore + "," + student.javaScore + "," + student.avgScore + "," +
                               student.result);
            }
            System.out.println("Danh sách học viên đã được ghi vào file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đọc dữ liệu từ file và thực hiện các công việc sau
        try (Scanner fileScanner = new Scanner(new File("students.txt"))) {
            List<Student> studentsFromFile = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                Student student = new Student(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]),
                                               Double.parseDouble(parts[4]));
                studentsFromFile.add(student);
            }

            // Sắp xếp học viên theo điểm trung bình giảm dần
            Collections.sort(studentsFromFile);

            // In danh sách học viên và ghi ra file
            try (PrintWriter writer = new PrintWriter("sorted_students.txt")) {
                System.out.println("\nDanh sách học viên sau khi sắp xếp:");
                System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s%-10s\n",
                                  "Mã SV", "Họ và tên", "Giới tính", "Điểm Python", "Điểm Java", "Điểm TB", "Kết quả");
                for (Student student : studentsFromFile) {
                    System.out.println(student);
                    writer.println(student.id + "," + student.fullName + "," + student.gender + "," +
                                   student.pythonScore + "," + student.javaScore + "," + student.avgScore + "," +
                                   student.result);
                }
                System.out.println("Danh sách học viên đã được sắp xếp và ghi vào file.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Nhập vào họ tên học viên cần tìm
            System.out.print("\nNhập vào họ tên học viên cần tìm: ");
            String searchName = scanner.nextLine();
            boolean found = false;
            System.out.println("Kết quả tìm kiếm:");
            for (Student student : studentsFromFile) {
                if (student.fullName.equalsIgnoreCase(searchName)) {
                    System.out.println(student);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Học viên không tìm thấy.");
            }

            // Hiển thị thông tin những bạn đã đậu
            System.out.println("\nThông tin những bạn đã đậu:");
            for (Student student : studentsFromFile) {
                if (student.result.equals("Đậu")) {
                    System.out.println(student);
                }
            }

            // Hiển thị thông tin những bạn đã trượt
            System.out.println("\nThông tin những bạn đã trượt:");
            for (Student student : studentsFromFile) {
                if (student.result.equals("Trượt")) {
                    System.out.println(student);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}