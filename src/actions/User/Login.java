package actions.User;

import behindTheScenes.Passenger;
import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.User;
import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class Login extends UserAction {
    public String className;
    public Login(String className) {
        super("Login", "L");
        this.className = className;
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        
        String username = input("Username: ", e -> Validations.validString(e));
        String password = input("Password: ", e -> Validations.validPass(e));

        switch (className) {
            case Passenger.className:
                user = User.login(username, password, Passenger.instances);
                break;
            case Manager.className:
                user = User.login(username, password, Manager.instances);
                break;
            case Driver.className:
                user = User.login(username, password, Driver.instances);
                break;
            default:
                return new User();
        }
        return user;
    }
}
