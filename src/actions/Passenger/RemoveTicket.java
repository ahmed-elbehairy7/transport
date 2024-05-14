package actions.Passenger;

import actions.User.UserAction;
import behindTheScenes.Ticket;
import behindTheScenes.User;
import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class RemoveTicket extends UserAction{
    public RemoveTicket() {
        super("Remove A Ticket", "R");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction,
            boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        print(Ticket._listTickets(user.id));
        String ans = input("\n\nTicket id:\n", e -> Validations.validInt(e));
        int id = Integer.parseInt(ans);

        Ticket.removeInstance(id);

        return user;
    }
}
