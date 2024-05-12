package bts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ticket {

    public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    public static String CSVOPEN = "id,passengerId,tripId";
    public static String TICKETSFILE = "tickets.csv";

    public int id;
    public int passengerId;
    public int tripId;
    
    public Ticket(int passengerId, int tripId) {
        if (tickets.size() == 0) {
            this.id = 1;
        }
        else {
            this.id = tickets.getLast().id + 1;
        }
        this.passengerId = passengerId;
        this.tripId = tripId;

        tickets.add(this);
    }
    public Ticket(int id, int passengerId, int tripId) {
        this.id = id;
        this.passengerId = passengerId;
        this.tripId = tripId;

        tickets.add(this);
    }

    public static void getTickets() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(TICKETSFILE));
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] ticketData = line.split(",");

                new Ticket(Integer.parseInt(ticketData[0]), Integer.parseInt(ticketData[1]),
                        Integer.parseInt(ticketData[2]));
            }
        } catch (FileNotFoundException e) {
            saveTickets();
            getTickets();
        } catch (IOException e) {
            System.out.println("An error occured while getting stored trips");
            e.printStackTrace();
        }
    }

    public void addTicket() {
        Common.writeToFile(TICKETSFILE, toCsv());
    }

    public String toCsv() {
        return this.id + "," + this.passengerId + "," + this.tripId;
    }

    public String toString() {
        Trip trip;

        for (short i = 0; i < Trip.trips.size(); i++) {
            trip = Trip.trips.get(i);
            if (trip.id == this.tripId) {
                return trip.toString("Ticket ID: " + this.id + "\nPassenger ID" + this.passengerId + "\nTrip ID"
                        + this.tripId + "\n\nTrip Info:\n---" + trip.info());
            }
        }
        return "";
    }

    public static void removeTicket(int id) {
        for (short i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).id == id) {
                tickets.remove(i);
                saveTickets();
                System.out.println("\n\nTicket with id " + id + " successfully deleted\n\n");
            }
        }

    }
    public static void saveTickets() {
        Common._writeToFile(TICKETSFILE, CSVOPEN, false);

        for (short i = 0; i < tickets.size(); i++) {
            tickets.get(i).addTicket();
        }
    }

    public static void listTickets(int passengerId) {
        for (short i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (ticket.passengerId == passengerId) {
                System.out.println(ticket.toString());
            }
        }
    }
}
