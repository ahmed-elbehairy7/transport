package flows;

import actions.Manager.ManagerActions;
import behindTheScenes.Manager;
import behindTheScenes.User;
import gui.components.TextArea;

public class ManagerFlow extends UserFlow {
    
    public ManagerFlow(User user, boolean gui) {
        super(ManagerActions.actions(), gui, Manager.className);
        if (gui) {
            startGuiFlow(actions, new TextArea());
            return;
        }
        startCliFlow(actions);
        
    }

}
