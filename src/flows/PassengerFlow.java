package flows;

import actions.Passenger.PassengerActions;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import gui.components.TextArea;

public class PassengerFlow extends UserFlow {
    
    public PassengerFlow(User user, boolean gui) {
        super(PassengerActions.actions(), gui, Passenger.className);
        if (gui) {
            startGuiFlow(actions, new TextArea());
            return;
        }
        startCliFlow(actions);
        
    }

}
