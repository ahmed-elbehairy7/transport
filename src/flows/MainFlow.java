package flows;

import actions.Main.MainActions;

public class MainFlow extends Flow {
    
    public MainFlow(boolean gui) {
        super("Transport", MainActions.actions());

        if (gui) {
            startGuiFlow();
            return;
        }
        startCliFlow();
        
    }

}