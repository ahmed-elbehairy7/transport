import java.util.Scanner;

import javax.swing.text.Style;

import bts.*;

public class Main {
    public static void main(String[] args)  {
            
        /* 
        A user will start the program with either registering a new account or logging in and then select which kind of user is using the program a passenger or an employee.
        */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a user type:\n\n(P) Passenger:\n(M) Manager:\n(D) Driver:\n\n");

        switch (scanner.nextLine().toUpperCase()) {
            case "P":
                Passenger passenger = new Passenger();
                passenger.startFlow();
                break;
            case "M":
                Manager manager = new Manager();
                manager.startFlow();
                 break;
            case "D":
                Driver driver = new Driver();
                driver.startFlow();
                break;
        
            default:
                System.out.println("\nInvalid user type!");
                break;
        }
    }
}