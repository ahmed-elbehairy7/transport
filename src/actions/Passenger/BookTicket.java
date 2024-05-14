package actions.Passenger;

import actions.User.UserAction;
import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;
import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class BookTicket extends UserAction {
    public BookTicket() {
        super("Book a ticket", "B");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        this.inputFunc = inputFunction;
        this.outputFunc = outputFunction;

        new ListTrips().startUserFlow(user, inputFunction, outputFunction, gui);
        print("Please enter the id of the trip: ");
        String id = input("Trip id: ", e -> Validations.validInt(e));
        Trip trip = (Trip) Trip.getById(id);

        if (trip.seats == 0) {
            print("\n\nSorry, the requested trip doesn't have enough seats, please choose another one! \n\n");
            return user;
        }

        print("\n\nHere's a detailed information about the selected trip:\n\n" + trip.toString(trip.allDetails())
                + "\n\nBook the ticket?\n");

        switch (input("Answer with Y(yes)/N(no): ", e -> Validations.validYesOrNo(e)).toUpperCase().charAt(0)) {
            case 'Y':
                new Ticket(user.id, trip.id);
                Ticket.saveInstances();
                trip.editInstance("5", (str, fun) -> trip.seats-- + "");;
                Trip.saveInstances();
                return user;
            default:
                return user;
        }


        
    }
}
