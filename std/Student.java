package std;

import java.io.*;
import java.util.*;

public class Student implements Comparable<Student> {
    String id;
    String fullName;
    String gender;
    double pythonScore;
    double javaScore;
    double avgScore;
    String result;

    public Student(String id, String fullName, String gender, double pythonScore, double javaScore) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.pythonScore = pythonScore;
        this.javaScore = javaScore;
        this.avgScore = (2 * javaScore + pythonScore) / 3;
        this.result = getResult();
    }

    private String getResult() {
        if (avgScore >= 5) {
            return "Đậu";
        } else if (avgScore < 5 && gender.equalsIgnoreCase("Nam")) {
            return "Trượt";
        } else if (avgScore < 5 && gender.equalsIgnoreCase("Nữ")) {
            return "Đậu";
        }
        return "";
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.avgScore, this.avgScore);
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10s%-10.1f%-10.1f%-10.1f%-10s",
                             id, fullName, gender, pythonScore, javaScore, avgScore, result);
    }
}