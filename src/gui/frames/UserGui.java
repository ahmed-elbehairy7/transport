package gui.frames;

import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;
import behindTheScenes.Vehicle;
import gui.components.Frame;

public class UserGui extends Frame {

    public User user;
    
    public UserGui(String title, User theUser) {
        super(title);
        user = theUser;

        Trip.initiateClass();
        Vehicle.initiateClass();
        Ticket.initiateClass();
    }
}
