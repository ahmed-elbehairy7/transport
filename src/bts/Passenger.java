package bts;

import java.util.Scanner;
import java.lang.System;

import bts.User;

public class Passenger extends User {

   
    public Passenger() {
        super("passenger");
    }

    public void startFlow() {
        /* 
        From the passenger profile he is able to select the trip he wants to make (source,destination,one-way,round-trip,number of stops … etc) from a list of available trips.
        */

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Please choose one of the following:\n\n(B) book a ticket\n(R) Review booked tickets\n\n");

            switch (scanner.nextLine().toUpperCase()) {
                case "B":
                        bookATicket(scanner);
                    break;
                case "R":
                    reviewTrips(scanner);
                    break;
                default:
                    break;
            }
        
        }
        
    }
    

    public void bookATicket(Scanner scanner) {

        System.out.println("Press enter to view the list of available trips");
        scanner.nextLine();

        System.out.println("List of the available trips:\n\n");

        for (short i = 0; i < Trip.trips.size(); i++) {
            Trip trip = Trip.trips.get(i);
            System.out.println(trip.toString("Index: " + i + "\n" + trip.info()));
        }

        System.out.println("please type the index of the trip you want to select, type nothing to exit: ");
        String index = scanner.nextLine();
        if (index.isEmpty()) {
            return;
        }
        Trip trip = Trip.trips.get(Integer.parseInt(index));

        if (trip.seats == 0) {
            System.out.println(
                    "\n\nSorry, the requested trip doesn't have enough seats, please choose another one! \n\n");

            bookATicket(scanner);
            return;
        }

        System.out.println("\n\nHere's a detailed information about the selected trip:\n\n");
        System.out.println(trip.toString(trip.allInfo()));

        /*
        When the passenger books a ticket (if there are available seats) he is shown a price for the selected ticket(s) and then proceeds to buy them.
        */

        System.out.println("Book a ticket? (Y/N)  ");

        switch (scanner.nextLine().toUpperCase()) {
            case "Y":

                break;

            default:
                return;
        }

    }

    public void reviewTrips(Scanner scanner) {

         /* 
         The passenger is able to review and cancel his tickets from his profile.
         */
        
         
    }
    
}
