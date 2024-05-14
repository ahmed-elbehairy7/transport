package actions.User;

import java.util.ArrayList;

public class RegActions {
    public static ArrayList<UserAction> actions(String className) {
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add((UserAction) new Login(className));
        actions.add((UserAction) new Register(className));
        return actions;
    }
}