package com.schoolManagementByRohail;


import java.beans.ConstructorProperties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainSecurity {

    private String registerUser, registerPassword;

    public void register() {
        UserManager userManager = new UserManager();
        userManager.register();
    }
    
    public void run() throws IOException {
        Scanner scan = new Scanner(new File("LoginDetails.txt"));
        Scanner scanner = new Scanner(System.in);

        String user = scan.nextLine();
        String pass = scan.nextLine();

        UserManager userManager = new UserManager();

        String listChoice;
        do {
            listChoice = userManager.showLoginOptions(scanner);

            if (userManager.isValidLoginChoice(listChoice)) {
                handleLoginChoice(listChoice, userManager, scanner, user, pass);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (!listChoice.equals("1") && !listChoice.equals("2") && !listChoice.equals("3"));
    }

    private void handleLoginChoice(String listChoice, UserManager userManager, Scanner scanner, String user, String pass) throws IOException {
        switch (listChoice) {
            case "1":
                handleAdminLogin(scanner, user, pass, userManager);
                break;
            case "2":
                handleTeacherLogin(scanner, user, pass, userManager);
                break;
            case "3":
                handleStudentLogin(scanner, user, pass, userManager);
                break;
        }
    }

    private void handleAdminLogin(Scanner scanner, String user, String pass, UserManager userManager) {
        System.out.println("\nPress 1 for Records Access");
        String adminAccess = scanner.nextLine();

        if (adminAccess.equals("1")) {
            handleAdminRecordAccess(scanner, user, pass, userManager);
        }
    }

    private void handleAdminRecordAccess(Scanner scanner, String user, String pass, UserManager userManager) {
        System.out.println("Press 1 for Modify Records");
        System.out.println("Press 2 for Delete Records");
        System.out.println("Press 3 for Show Records");
        String adminFeatures = scanner.nextLine();

        Admin admin = new Admin();
        if (adminFeatures.equals("1") || adminFeatures.equals("2")) {
            admin.personalRecordList(adminFeatures);
        } else if (adminFeatures.equals("3")) {
            admin.readPersonalDetails();
        }
    }

    private void handleTeacherLogin(Scanner scanner, String user, String pass, UserManager userManager) throws IOException {
        Teacher teacher = new Teacher();
        teacher.callSetters();
        teacher.getPersonalDetails();
        System.out.println("\n\n");
        System.out.println("Press 1 for Enter Attendance");
        System.out.println("Press 2 for Enter Marks/Grades");

        String teacherAccess = scanner.nextLine();
        if (teacherAccess.equals("1")) {
            teacher.attendance();
        } else if (teacherAccess.equals("2")) {
            teacher.marksRecord();
        } else {
            System.out.println("***** Only Press 1 or 2 *****");
        }
    }

    private void handleStudentLogin(Scanner scanner, String user, String pass, UserManager userManager) {
        Student student = new Student();
        student.callSetters();
        student.getPersonalDetails();
        System.out.println("\n\n");
        System.out.println("Press 1 for Attendance Details");
        System.out.println("Press 2 for Marks/Grades Details");

        String studentAccess = scanner.nextLine();
        if (studentAccess.equals("1")) {
            student.attendanceView();
        } else if (studentAccess.equals("2")) {
            student.marksAndGradesView();
        } else {
            System.out.println("***** Only Press 1 or 2 *****");
        }
    }
}
