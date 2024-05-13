package gui.frames;

import java.awt.event.ActionListener;

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

        String Type = InputData.gui("Type: ", e -> Validations.validTripType(e));
        String Source = InputData.gui("Source: ", e -> Validations.validString(e));
        String Destination = InputData.gui("Destination", e -> Validations.validString(e));
        String Stops = InputData.gui("Stops: ", e -> Validations.validInt(e));
        String Seats = InputData.gui("Seats: ", e -> Validations.validInt(e));
        String Price = InputData.gui("Price", e -> Validations.validInt(e));
        String DriverId = InputData.gui("DriverId", e -> Validations.validInt(e));
        String Cycle = InputData.gui("Cycle: ", e -> Validations.validCycle(e));

        new Trip(Type, Source, Destination, Integer.parseInt(Stops), Integer.parseInt(Seats), Integer.parseInt(Price), Integer.parseInt(DriverId), Cycle).writeInstance(Trip.savedPath);
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
        while (true) {
            outputArea.setText(trip.toString(trip.allInfo()));
            outputArea.append(
                    "\n\nPlease choose what to edit:\n\n(0) Source\n(1) Destination\n(2) Type\n(3) Stops\n(4) Seats\n(5) Price\n(6) Driver\n(S) Save and exit\n(Q) Quit without saving\n\n");

            switch (JOptionPane.showInputDialog("Please choose what to edit: ").toUpperCase()) {
                case "0":
                    trip.source = InputData.gui(prompt, e -> Validations.validName(e));
                    break;
                case "1":
                    trip.destination = InputData.gui(prompt, e -> Validations.validName(e));
                    break;
                case "2":
                    trip.type = InputData.gui(prompt, e -> Validations.validTripType(e));
                    break;
                case "3":
                    trip.stops = Integer.parseInt(InputData.gui(prompt, e -> Validations.validInt(e)));
                    break;
                case "4":
                    trip.seats = Integer.parseInt(InputData.gui(prompt, e -> Validations.validInt(e)));
                    break;
                case "5":
                    trip.price = Integer.parseInt(InputData.gui(prompt, e -> Validations.validInt(e)));
                    break;
                case "6":
                    trip.driverId = Integer.parseInt(InputData.gui(prompt, e -> Validations.validInt(e)));
                    break;
                case "S":
                    Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);
                    return;
                case "Q":
                    Trip.getSaved(Trip.instances, Trip.savedPath, Trip.className, Trip.csvHeader);
                    return;
                default:
                    break;
            }
        }
    }
    
    private void assignDriver() {
        
        listTrips();

        int id = Integer.parseInt(InputData.gui("Please type the id of the trip you want to edit: ", e -> Validations.validInt(e)));

        Trip trip = (Trip) Trip.getById(id, Trip.instances);

        outputArea.setText("\n\nChoosed Trip:\n");
        outputArea.append(trip.toString(trip.allInfo()));
        outputArea.append("\n\n\nAvailable drivers:\n");
        outputArea.append(Driver._listDrivers());
        trip.driverId = Integer.parseInt(InputData.gui("\nNew Driver Id: ", e -> Validations.validInt(e)));
        Trip.saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);

    }

    private void addVehicle() {
        String Type = InputData.gui("Type: ", e -> Validations.validName(e));
        int Capacity = Integer.parseInt(InputData.gui("Capacity: ", e -> Validations.validInt(e)));
        String LicensePlate = InputData.gui("License Plate: ", e -> Validations.validName(e));

        new Vehicle(Type, Capacity, LicensePlate);
        Vehicle.saveInstances(Vehicle.instances, Vehicle.savedPath, Vehicle.csvHeader);
    }

    private void addEmployee() {
        String Name = InputData.gui("Name: ", e -> Validations.validString(e));
        String Email = InputData.gui("Email: ", e -> Validations.validEmail(e));
        String Username = InputData.gui("Username: ", e -> Validations.validUsername(e, Driver.instances));
        String password = InputData.gui("Password: ", e -> Validations.validPass(e));
        new Driver(Name, Username, password, Email);
        Driver.saveInstances(Driver.instances, Driver.savedPath, Driver.csvHeader);
    }

}
