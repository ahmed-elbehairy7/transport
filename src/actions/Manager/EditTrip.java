package actions.Manager;

import actions.User.UserAction;
import behindTheScenes.Trip;
import behindTheScenes.User;
import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class EditTrip extends UserAction  {
    public EditTrip() {
        super("Edit a Trip", "E");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        print(Trip._listInstances());
        String id = input("Trip id: ", e -> Validations.validInt(e));
        Trip trip = (Trip) Trip.getById(id);

        while (true) {
            print(trip.toString(trip.allDetails()) + "\n\n" + Trip.editables());
            String keyIndex = input("What to edit: ", e -> true);
            switch (keyIndex.toUpperCase()) {
                case "S":
                    Trip.saveInstances();
                    return user;
                case "Q":
                    Trip.getSaved();
                    return user; 
                default:
                    trip.editInstance(keyIndex, inputFunc);
                    break;
        }
        }
    }
}
