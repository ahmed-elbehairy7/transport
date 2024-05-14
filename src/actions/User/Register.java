package actions.User;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class Register extends UserAction {
    public String className;
    public Register(String className) {
        super("Register", "R");
        this.className = className;
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction,
            boolean gui) {
        
        super.startUserFlow(user, inputFunction, outputFunction, gui);

        switch (className) {
            case Passenger.className:
                user = (User) Passenger.addInstance(inputFunc);
                break;
            case Manager.className:
                user = (User) Manager.addInstance(inputFunc);
                break;
            case Driver.className:
                user = (User) Driver.addInstance(inputFunc);
                break;
            default:
                return new User();
        }
        user.success = true;
        return user;
    }
}
