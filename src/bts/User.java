package bts;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bts.Common;

import java.lang.System;
import java.io.FileReader;
import java.io.BufferedReader;


public class User {
    public static String CSVHEADER = "id,Name,Email,username,password";
    public static String USERSFILE = "users.csv"; 
    public static String TYPES[]=
            { "passenger", "manager", "driver" };

    public int id;
    public String name;
    public String username;
    public String password;
    public String Email;
    public String type;
    public boolean success = false;
    
    public User(String type) {
        this.type = type;
        USERSFILE = this.type + "s.csv";

        //Initialize the scanner
        Scanner scanner = new Scanner(System.in);

        // Ask the user to register or login
        System.out.print("\nWelcome!\n\nPlease enter 'r' to register, any key to login: ");

        // Get what the user choose
        switch (scanner.nextLine()) {
            case "r":
                register();
                break;
            default:
                login();
                break;
        }

        if (!this.success) {
            System.exit(1);
        }

        Trip.getTrips();
    }

    public void register() {

        Scanner scanner = new Scanner(System.in);

        this.name = Common.getData(scanner, "Enter your name: ");
        this.Email = Common.getData(scanner, "Enter your email: ");
        this.username = Common.getData(scanner, "Enter your username: ");
        this.password = Common.getData(scanner, "Enter your password: ");

        String userData = this.name + "," + this.Email + "," + this.username + "," + this.password;

        try {

            BufferedReader br = new BufferedReader(new FileReader(USERSFILE));
            String temp = br.readLine();
            String line;
            
            do {
                line = temp;
            } while ((temp = br.readLine()) != null);

            this.id = Integer.parseInt(line.split(",")[0]) + 1;
            
        } catch (FileNotFoundException e) {
            Common.writeToFile(USERSFILE, CSVHEADER);
            Common.writeToFile(USERSFILE, "1," + userData);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        System.out.println("\nyour Registration successful.\n");
        this.success = true;
    }

    public boolean login() {

        Scanner scanner = new Scanner(System.in);

        String username = Common.getData(scanner, "Enter your username: ");
        String password = Common.getData(scanner, "Enter your password: ");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERSFILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 5 && userData[3].equals(username) && userData[4].equals(password)) {
                    this.id = Integer.parseInt(userData[0]);
                    this.name = userData[1];
                    this.Email = userData[2];
                    this.username = userData[3];
                    this.password = userData[4];
                    this.success = true;
                    System.out.println("\n\n\nWelcome " + this.type + "!\n\n");
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