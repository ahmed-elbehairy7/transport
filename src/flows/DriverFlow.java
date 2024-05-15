package flows;

import actions.Driver.DriverActions;
import behindTheScenes.Driver;
import behindTheScenes.User;

public class DriverFlow extends UserFlow {
    
    public DriverFlow(User user, boolean gui) {
        super(Driver.className, DriverActions.actions(user), gui, Driver.className);
        if (gui) {
            startGuiFlow();
        }
        else {
            startCliFlow();
        }
        
    }

}
