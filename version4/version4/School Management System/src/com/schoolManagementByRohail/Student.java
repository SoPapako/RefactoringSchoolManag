package com.schoolManagementByRohail;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Student extends Teacher {

    Scanner scanner = new Scanner(System.in);
    private int studentRollNumber;

    Student() {
    } // free constructor

    void attendanceView() {
        System.out.println("Enter your Roll Number");
        studentRollNumber = scanner.nextInt();
        readDetailsFromFile("AttendanceRecord.txt");
    }

    void marksAndGradesView() {
        System.out.println("Enter your Roll Number");
        studentRollNumber = scanner.nextInt();
        readDetailsFromFile("MarksAndGradesRecord.txt");
    }

    private void readDetailsFromFile(String fileName) {
        try {
            FileReader detailsReader = new FileReader(fileName);

            String specificDetails = Files.readAllLines(Paths.get(fileName)).get(studentRollNumber - 1);
            System.out.println(specificDetails);

            detailsReader.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
