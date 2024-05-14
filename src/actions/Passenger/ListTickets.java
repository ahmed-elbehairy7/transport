package actions.Passenger;

import actions.User.UserAction;
import behindTheScenes.Ticket;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class ListTickets extends UserAction  {
    public ListTickets() {
        super("List Tickets", "T");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        print(Ticket._listTickets(user.id));
        return user;
    }
}
