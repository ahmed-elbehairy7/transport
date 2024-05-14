package actions.Passenger;

import java.util.ArrayList;

import actions.User.UserAction;

public class PassengerActions {
    public static ListTrips listTrips = new ListTrips();
    public static BookTicket bookTicket = new BookTicket();
    public static ListTickets listTickets = new ListTickets();
    public static RemoveTicket removeTicket = new RemoveTicket();

    public static ArrayList<UserAction> actions() {
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add((UserAction) listTrips);
        actions.add((UserAction) bookTicket);
        actions.add((UserAction) listTickets);
        actions.add((UserAction) removeTicket);
        return actions;
    }
}
