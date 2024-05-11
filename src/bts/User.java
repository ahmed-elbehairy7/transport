package bts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bts.FIO;

import java.lang.System;
import java.io.FileReader;
import java.io.BufferedReader;


public class User {
    public static String USERSFILE = "users.csv"; 
    public static String TYPES[]=
    {"passenger", "manager", "driver"};

    public String name;
    public String username;
    public String password;
    public String Email;
    public String type;
    public boolean success = false;
    
    public User(String type, String usersFile) {
        USERSFILE = usersFile;
        this.type = type;

        //Initialize the scanner
        Scanner scanner = new Scanner(System.in);

        // Ask the user to register or login
        System.out.print("\nWelcome!\n\nPlease enter 'l' to login, any key to register: ");

        // Get what the user choose
        String next = scanner.nextLine();

        switch (next) {
            case "l":
                login();
                break;
            default:
                register();
                break;
        }

        scanner.close();
    }

    public void register() {

        Scanner scanner = new Scanner(System.in);

        this.name = getData(scanner, "Enter your name: ");
        this.Email = getData(scanner, "Enter your email: ");
        this.username = getData(scanner, "Enter your username: ");
        this.password = getData(scanner, "Enter your password: ");

        String userData = this.name + "," + this.Email + "," + this.username + "," + this.password;
        if (FIO.writeToFile(USERSFILE, userData)) {
            System.out.println("your Registration successful.");
            this.success = true;
        }
    }
    
    private String getData(Scanner scanner, String prompt) {
        String data;
        do {
            System.out.print(prompt);
            data = scanner.nextLine();
        } while (data.length() < 4);
        return data;
    }

    public boolean login() {
        
        Scanner scanner = new Scanner(System.in);

        String username = getData(scanner, "Enter your username: ");
        String password = getData(scanner, "Enter your password: ");

        system sys = new system(username, password);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERSFILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[2].equals(username) && userData[3].equals(password)) {
                    this.name = userData[0];
                    this.Email = userData[1];
                    this.username = userData[2];
                    this.password = userData[3];
                    this.type = userData[4];
                    this.success = true;
                    System.out.println("Welcome !");
                    return true;
                }
            }
            System.out.println("Invalid username or password. Please try again.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return false;
    }
}