package flows;

import actions.Passenger.PassengerActions;
import behindTheScenes.Passenger;
import behindTheScenes.User;

public class PassengerFlow extends UserFlow {
    
    public PassengerFlow(User user, boolean gui) {
        super(Passenger.className, PassengerActions.actions(), gui, Passenger.className);
        if (gui) {
            startGuiFlow();
        }
        else {
            startCliFlow();
        }
    }

}
