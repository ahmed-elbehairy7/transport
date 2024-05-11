import java.util.Scanner;

import javax.swing.text.Style;

import bts.Passenger;

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
                /* 
                From the passenger profile he is able to select the trip he wants to make (source,destination,one-way,round-trip,number of stops … etc) from a list of available trips.
                */

                /*
                 When the passenger books a ticket (if there are available seats) he is shown a price for the selected ticket(s) and then proceeds to buy them.
                 */
                /* 
                The passenger is able to review and cancel his tickets from his profile.
                */
                break;
            // case "M":
            //     user = new Manager();
            //     // - If you log in as a manager you are able to review all trips in the system,you are able
            //     // to add / remove trips and assign drivers to the trips in the system
            //     break;
            // case "D":
            //     user = new Driver();
            //     // - If you log in with a driver credentials you are directed to the drivers profile with
            // // some basic information about the driver and the trips that are assigned to him by
            // // the manager.
            //     break;
        
            default:
                System.out.println("\nInvalid user type!");
                break;
        }
    }
}