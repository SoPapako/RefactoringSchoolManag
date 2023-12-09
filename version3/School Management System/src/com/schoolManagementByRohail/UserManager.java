package com.schoolManagementByRohail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserManager {

    private String registerUser, registerPassword;

    public void register() {
        Scanner scanner = new Scanner(System.in);

        try {
            FileWriter loginWriter = new FileWriter("LoginDetails.txt");
            System.out.println("Enter your user Name");
            registerUser = scanner.nextLine();
            loginWriter.write(registerUser + "\n");
            System.out.println("Enter your Password");
            registerPassword = scanner.nextLine();
            loginWriter.write(registerPassword + "\n");
            loginWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String user, String pass, Scanner scanner) {
        System.out.println("Enter your user Name");
        String inpUser = scanner.nextLine();
        System.out.println("Enter your Password");
        String inpPass = scanner.nextLine();
        return inpUser.equals(user) && inpPass.equals(pass);
    }

    public String showLoginOptions(Scanner scanner) {
        System.out.println("You can log in as the following domains");
        System.out.println("Enter 1 for admin");
        System.out.println("Enter 2 for teacher");
        System.out.println("Enter 3 for student");
        return scanner.nextLine();
    }

    public boolean isValidLoginChoice(String listChoice) {
        return listChoice.equals("1") || listChoice.equals("2") || listChoice.equals("3");
    }
}
