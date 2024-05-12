package bts;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bts.Savable;
import bts.Ticket;
import bts.Vehicle;

import java.lang.System;
import java.io.FileReader;
import java.io.BufferedReader;


public class User extends Savable{
    public static String csvHeader = "id,Name,Email,username,password";

    public String name;
    public String username;
    public String password;
    public String Email;
    public boolean success = false;
    
    public User(int id, String name, String username, String password, String Email) {
        this.id = id;

        this.name = name;
        this.Email = Email;
        this.username = username;
        this.password = password;

    }

    public User(ArrayList<Savable> instances, String savedPath, String className) {
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

        String t;
        switch (className) {
            case Passenger.className:
                t = "puser";
                break;
            case Manager.className:
                t = "muser";
                break;
            case Driver.className:
                t = "duser";
            default:
                t = "user";
                break;
        }

        this.name = getData(scanner, "Enter your name: ");
        this.Email = _getData(scanner, "Enter your email: ", "email");
        this.username = _getData(scanner, "Enter your username: ", t);
        this.password = _getData(scanner, "Enter your password: ", "pass");
        this.id = generateId(instances);

        writeInstance(savedPath);

        this.success = true;
    }
    
    public String toCsv() {
        return this.id + "," + this.name + "," + this.Email + "," + this.username + "," + this.password;
        
    }

    public boolean login(ArrayList<Savable> instances) {

        Scanner scanner = new Scanner(System.in);

        String username = getData(scanner, "Enter your username: ");
        String password = _getData(scanner, "Enter your password: ", "pass");

        User user;
        for (short i = 0; i < instances.size(); i++) {
            user = ((User) instances.get(i));
            if (!(user.username.equals(username))) {
                continue;
            }
            if (!(user.password.equals(password))) {
                continue;
            }
            this.id = user.id;
            this.name = user.name;
            this.username = user.username;
            this.Email = user.Email;
            this.password = user.password;
            this.success = true;
            return true;
        }
        System.out.println("Invalid username or password");
        return false;
    }
   
    public static void initiateClass(String usersFile, String className, ArrayList<Savable> instances) {
        initiateClass(usersFile, User.csvHeader, className, instances);
    }
}