package flows;

import actions.Manager.ManagerActions;
import behindTheScenes.Manager;
import behindTheScenes.User;

public class ManagerFlow extends UserFlow {
    
    public ManagerFlow(User user, boolean gui) {
        super(Manager.className, ManagerActions.actions(), gui, Manager.className);
        if (gui) {
            startGuiFlow();
        }
        else {
            startCliFlow();
        }
        
    }

}
