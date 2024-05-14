package flows;

import actions.Main.MainActions;
import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import gui.components.TextArea;

public class MainFlow extends Flow {
    
    public MainFlow(boolean gui) {
        super(MainActions.actions());

        Passenger.initiateClass();
        Manager.initiateClass();
        Driver.initiateClass();

        if (gui) {
            startGuiFlow(new TextArea());
            return;
        }
        startCliFlow();
        
    }

}