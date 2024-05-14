package gui.frames;

import java.awt.event.ActionListener;

import behindTheScenes.Trip;
import behindTheScenes.User;

public class DriverGui extends UserGui {

    public DriverGui(User Driver) {
        super("Driver", Driver);

        String[] buttonsText = {"Personal Info", "List Assigned Trips"};
        ActionListener[] functions = {e -> personalInfo(), e -> listAssignedTrips()};

        generateUi(buttonsText, functions);

    }
    
    private void personalInfo() {
        outputArea.setText(user.toString());
    }

    private void listAssignedTrips() {
        outputArea.setText("\n\nHere's the trips assigned to you: \n");
        for (short i = 0; i < Trip.instances.size(); i++) {
            Trip trip = (Trip) Trip.instances.get(i);
            if (user.id ==trip.driverId) {
                outputArea.append(trip.toString(trip.displayText()));
            }
        }
    }

}
