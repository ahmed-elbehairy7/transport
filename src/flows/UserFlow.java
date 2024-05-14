package flows;

import java.util.ArrayList;

import actions.Common.PersonalInfo;
import actions.Common.Quit;
import actions.User.RegActions;
import actions.User.UserAction;
import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;
import behindTheScenes.Vehicle;
import gui.components.TextArea;

public class UserFlow extends Flow {
    public User user;
    public ArrayList<UserAction> regActions;
    public ArrayList<UserAction> actions;
    public PersonalInfo personalInfo = new PersonalInfo();
    public UserAction quit = new Quit();

    UserFlow(ArrayList<UserAction> actions, boolean gui, String className) {
        super();

        initializeActions(actions, className);
        
        if (gui) {
            startGuiFlow(regActions, new TextArea(), true);
            return;
        }
        startCliFlow(regActions, true);

        if (!user.success) {
            cliOutput.print("\n\nInvalid credentials\n\n");
            guiOutput.print("\n\nInvalid credentials\n\n");
        }

        Trip.initiateClass();
        Vehicle.initiateClass();
        Ticket.initiateClass();
    }
    
    public void initializeActions(ArrayList<UserAction> actions, String className) {

        this.regActions = RegActions.actions(className);
        this.regActions.add(quit);

        this.actions = new ArrayList<>();
        this.actions.add(personalInfo);
        this.actions.addAll(actions);
        this.actions.add(quit);
    }
    
    public void startGuiFlow(ArrayList<UserAction> Actions, TextArea outputArea) {
        startGuiFlow(Actions, outputArea, false);
    }

    public void startGuiFlow(ArrayList<UserAction> Actions, TextArea outputArea, boolean reg) {
        this.guiOutput = (str) -> outputArea.setText(str);

    }
    public void startCliFlow(ArrayList<UserAction> Actions, boolean reg) {
        while (true) {

            String choosedAction = chooseAction(Actions);

            for (byte i = 0; i < Actions.size(); i++) {
                if (Actions.get(i).cliChar.equals(choosedAction)) {
                    user = Actions.get(i).startUserFlow(user, cliInput, cliOutput, false);
                }
            }

            if (!reg || user == null) {
                continue;
            }
            if (!user.success) {
                System.out.println("\n\nInvalid credentials\n\n");
                continue;
            }
            return;
            
        }
    }

    public void startCliFlow(ArrayList<UserAction> Actions) {
        startCliFlow(Actions, false);
    }
    
}
