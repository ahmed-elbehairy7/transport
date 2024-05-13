package cli;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;

import behindTheScenes.*;
import data.*;
import functions.booleanFunction;


public class UserCli {
    public static String csvHeader = "id,Name,Email,username,password";

    public int id;
    public String name;
    public String username;
    public String password;
    public String Email;
    public boolean success = false;
    

    public UserCli(ArrayList<Savable> instances, String savedPath, String className) {
        //Initialize the scanner
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user to register or login
        System.out.print("\nWelcome!\n\nPlease enter 'r' to register, any key to login: ");

        // Get what the user choose
        switch (scanner.nextLine()) {
            case "r":
                register(instances, savedPath, className);
                break;
            default:
                login(instances);
                break;
        }

        if (!this.success) {
            System.out.println("Invalid credentials");
            System.exit(1);
        }

        Trip.initiateClass();
        Vehicle.initiateClass();
        Ticket.initiateClass();
    }

    public void register(ArrayList<Savable> instances, String savedPath, String className) {

        Scanner scanner = new Scanner(System.in);

        booleanFunction t;
        switch (className) {
            case Passenger.className:
                t = e -> Validations.validUsername(e, Passenger.instances);
                break;
            case Manager.className:
                t = e -> Validations.validUsername(e, Manager.instances);
                break;
            case Driver.className:
                t = e -> Validations.validUsername(e, Driver.instances);
        }

        this.name = InputData.cli(scanner, "Enter your name: ", e -> Validations.validName(e));
        this.Email = InputData.cli(scanner, "Enter your email: ", e -> Validations.validEmail(e));
        this.username = InputData.cli(scanner, "Enter your username: ", t);
        this.password = InputData.cli(scanner, "Enter your password: ", e -> Validations.validPass(e));

        User user = User.register(name, Email, username, password, instances, savedPath, className);
        this.id = user.id;
        this.success = true;
    }
    
    public String toCsv() {
        return this.id + "," + this.name + "," + this.Email + "," + this.username + "," + this.password;
        
    }

    public boolean login(ArrayList<Savable> instances) {

        Scanner scanner = new Scanner(System.in);

        String username = InputData.cli(scanner, "Enter your username: ");
        String password = InputData.cli(scanner, "Enter your password: ", e -> Validations.validPass(e));

        User user = User.login(username, password, instances);
        if (!user.success) {
            return false;
        }
        this.id = user.id;
        this.name = user.name;
        this.Email = user.Email;
        this.username = user.username;
        this.success = true;
        this.password = password;
        return true;
        
    }
   
    public static void initiateClass(String usersFile, String className, ArrayList<Savable> instances) {
        User.initiateClass(usersFile, User.csvHeader, className, instances);
    }
}