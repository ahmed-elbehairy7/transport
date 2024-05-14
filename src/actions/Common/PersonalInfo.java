package actions.Common;

import java.awt.List;
import java.util.ArrayList;

import actions.User.UserAction;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;
import functions.stringToVoid;

public class PersonalInfo extends UserAction {
    public PersonalInfo() {
        super("Personal Info", "P");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString input, stringToVoid output, boolean gui) {
        super.startUserFlow(user, input, output, gui);
        String keyIndex;
        Class<?> instanceClass = user.getClass();
        while (true) {
            print(user.toString());
            print(User.editables());
            keyIndex = input("What shall you do: ", e -> true).toUpperCase();
            //     switch (keyIndex) {
            //         case "S":
            //             User.saveInstances(instanceClass.getField("instances"), instanceClass.getField("savedPath"));
            //             break;
            //         case "Q":
            //             User.getSaved(instanceClass.getField("instances"), instanceClass.getField("savedPath"), instanceClass.getField("className"));
            //         default:
            //             instanceClass.editInstance(keyIndex, instanceClass.getField("instances").get(0), instanceClass.getField("validators").get(0), instanceClass.getField("className").get(0), instanceClass.getField("savedPath").get(0), instanceClass.getField("csvHeader").get(0), inputFunc );
            //             break;
            //     }
            return user;
        }
    }
}
