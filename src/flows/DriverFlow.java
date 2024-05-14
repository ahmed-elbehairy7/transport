package flows;

import actions.Driver.DriverActions;
import behindTheScenes.Driver;
import behindTheScenes.User;
import gui.components.TextArea;

public class DriverFlow extends UserFlow {
    
    public DriverFlow(User user, boolean gui) {
        super(DriverActions.actions(user), gui, Driver.className);
        if (gui) {
            startGuiFlow(actions, new TextArea());
            return;
        }
        startCliFlow(actions);
        
    }

}
