package gui.frames;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import behindTheScenes.Driver;
import behindTheScenes.Trip;
import behindTheScenes.User;
import behindTheScenes.Vehicle;
import data.InputData;
import data.Validations;

public class ManagerGui extends UserGui {

    public ManagerGui(User Manager) {
        super("Manager", Manager);

        String[] buttonsText = { "List Trips", "Add A Trip", "Remove A Trip", "Assign A Driver", "Add A Vehicle",
                "Add An Employee" };
        ActionListener[] functions = { e -> listTrips(), e -> addTrip(), e -> removeTrip(), e -> editTrip(),
                e -> assignDriver(), e -> addVehicle(), e -> addEmployee() };

        generateUi(buttonsText, functions);

    }
    
    private void listTrips() {
        outputArea.setText(Trip._listInstances(Trip.instances));
    }
    
    private void addTrip() {

        Trip.addInstance(true);
    }

    private void removeTrip() {
        listTrips();

        String id = InputData.gui("Please type the id of the trip you want to remove: ", e -> Validations.validInt(e));

        Trip.removeInstance(Integer.parseInt(id), Trip.instances, Trip.savedPath, Trip.csvHeader);

    }
    
    private void editTrip() {

        listTrips();

        int id = Integer.parseInt(InputData.gui("Please type the id of the trip you want to edit: ", e -> Validations.validInt(e)));

        Trip trip = (Trip) Trip.getById(id, Trip.instances);
        String prompt = "New value:\n";
        String keyIndex;
        while (true) {
            outputArea.setText(trip.toString(trip.allInfo()));
            outputArea.append("\n\n" + Trip.editables() + "(S) Save and exit\n(Q) Quit without saving\n\n");
            keyIndex = JOptionPane.showInputDialog("Please choose what to edit: ").toUpperCase();
            switch (keyIndex) {
                case "S":
                    Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
                    return;
                case "Q":
                    Trip.getSaved(Trip.instances, Trip.savedPath, Trip.className, Trip.csvHeader);
                    return;
                default:
                    trip.editInstance(keyIndex, true);
                    break;
            }
        }
    }
    
    private void assignDriver() {
        
        listTrips();

        int id = Integer.parseInt(InputData.gui("Please type the id of the trip you want to edit: ", e -> Validations.validInt(e)));

        Trip trip = (Trip) Trip.getById(id, Trip.instances);

        outputArea.setText("\n\nChoosed Trip:\n" + trip.toString(trip.allInfo()) + "\n\n\nAvailable drivers:\n" + Driver._listDrivers());
        trip.editInstance("7",true);;
        Trip.saveInstances();

    }

    private void addVehicle() {
        Vehicle.addInstance(true);
    }

    private void addEmployee() {
        Driver.addInstance(true);
    }

}
