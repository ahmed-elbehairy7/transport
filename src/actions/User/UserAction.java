package actions.User;

import actions.Action;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class UserAction extends Action {
    
    public UserAction(String prompt, String cliChar) {
        super(prompt, cliChar);
    }
    
    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startFlow(inputFunction, outputFunction, gui);
        return new User();
    }
}
