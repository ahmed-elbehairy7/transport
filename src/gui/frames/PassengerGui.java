package gui.frames;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;

public class PassengerGui extends UserGui {

    public PassengerGui(User passenger) {
        super("Passenger", passenger);

        String[] buttonsText = { "Book A Ticket", "List Tickets", "Remove A Ticket" };
        ActionListener[] functions = { e -> bookATicket(), e -> listTickets(), e -> removeTicket() };

        generateUi(buttonsText, functions);
        
    }

    private void bookATicket() {
        outputArea.setText("\n\n" + Trip._listInstances(Trip.instances));
        String ans = JOptionPane.showInputDialog("Type the index of the trip you want to select");

        int index = Integer.parseInt(ans);
        Trip trip = (Trip) Trip.instances.get(index);

        if (trip.seats == 0) {
            outputArea.setText(
                    "\n\nSorry, the requested trip doesn't have enough seats, please choose another one! \n\n\n\n");

            bookATicket();
            return;
        }

        outputArea.setText("\n\nHere's a detailed information about the selected trip:\n\n");
        outputArea.append(trip.toString(trip.allInfo()));

        /*
        When the passenger books a ticket (if there are available seats) he is shown a price for the selected ticket(s) and then proceeds to buy them.
        */

        if (JOptionPane.showConfirmDialog(this, "book the ticket?") == 0) {
            new Ticket(user.id, trip.id);
            Ticket.saveInstances(Ticket.instances, Ticket.savedPath, Ticket.csvHeader);
            ((Trip) Trip.instances.get(index)).seats -= 1;
            Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
            outputArea.setText("Your text had been booked successfully");
        }

    }

    private void listTickets() {
        outputArea.setText("Here's the available tickets\n\n");
        outputArea.append(Ticket._listTickets(user.id));

    }

    private void removeTicket() {
        
        listTickets();

        String ans = JOptionPane.showInputDialog("Please type the id of the ticket you want to delete");

        int id = Integer.parseInt(ans);
        
        for (short i = 0; i < Ticket.instances.size(); i++) {
            if (Ticket.instances.get(i).id == id) {
                Ticket.removeInstance(id, Ticket.instances, Ticket.savedPath, Ticket.csvHeader);
            }
        }

        listTickets();
    }
}
