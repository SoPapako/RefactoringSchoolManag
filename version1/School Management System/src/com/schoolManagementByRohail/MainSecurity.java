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
        Scanner scanner = new Scanner(System.in);


        try {
            FileWriter loginWritter = new FileWriter("LoginDetails.txt");
            System.out.println("Enter your user Name");
            registerUser = scanner.nextLine();
            loginWritter.write(registerUser + "\n");
            System.out.println("Enter your Password");
            registerPassword = scanner.nextLine();
            loginWritter.write(registerPassword + "\n");
            loginWritter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void run() throws IOException {
        Scanner scan = new Scanner(new File("LoginDetails.txt"));
        Scanner keyboard = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);  // Declare the scanner here
        String user = scan.nextLine();
        String pass = scan.nextLine();

        if (authenticateUser(user, pass, scanner)) {
            String listChoice = showLoginOptions(scanner);

            if (isValidLoginChoice(listChoice, scanner)) {
                handleLoginChoice(listChoice, scanner);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.print("Invalid username or password");
        }
    }

    private boolean authenticateUser(String user, String pass, Scanner scanner) {
        System.out.println("Enter your user Name");
        String inpUser = scanner.nextLine();
        System.out.println("Enter your Password");
        String inpPass = scanner.nextLine();
        return inpUser.equals(user) && inpPass.equals(pass);
    }

    private String showLoginOptions(Scanner scanner) {
        System.out.println("You can log in as the following domains");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("admin", "teacher", "student"));
        System.out.println(list);
        System.out.println("Enter any of the options mentioned above");
        System.out.println("Note that the spelling of your choice matches with the above options");
        return scanner.nextLine();
    }

    private boolean isValidLoginChoice(String listChoice, Scanner scanner) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("admin", "teacher", "student"));
        return list.contains(listChoice);
    }

    private void handleLoginChoice(String listChoice, Scanner scanner) throws IOException {
        switch (listChoice) {
            case "admin":
                handleAdminLogin(scanner);
                break;
            case "teacher":
                handleTeacherLogin(scanner);
                break;
            case "student":
                handleStudentLogin(scanner);
                break;
        }
    }

    private void handleAdminLogin(Scanner scanner) {
        System.out.println("\nPress 1 for Records Access");
        String adminAccess = scanner.nextLine();

        if (adminAccess.equals("1")) {
            handleAdminRecordAccess(scanner);
        }
    }

    private void handleAdminRecordAccess(Scanner scanner) {
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

    private void handleTeacherLogin(Scanner scanner) throws IOException {
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

    private void handleStudentLogin(Scanner scanner) {
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
