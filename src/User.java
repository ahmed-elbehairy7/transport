// imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.System;
import java.io.FileReader;
import java.io.BufferedReader;


// class user
public class User {
protected String name;
    protected String RegisterU;
    protected String RegisterP;
    protected String Email;
    public User() {
    }
    public void register(String fileName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
         name = scanner.nextLine();
        System.out.print("Enter your email: ");
         Email = scanner.nextLine();
        System.out.print("Enter your username: ");
         RegisterU=(scanner.nextLine());
        System.out.print("Enter your password: ");
         RegisterP=(scanner.nextLine());
        String userData = name + "," + Email + "," + RegisterU + "," + RegisterP;
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userData);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println("your Registration successful.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
    public boolean login(system system,String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[2].equals(system.getUsername()) && userData[3].equals(system.getPassword())) {
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