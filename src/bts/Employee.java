package bts;
import java.util.Scanner;
public class Employee extends User {
    public Employee(String type, String usersFile) {
        super(type, usersFile);
    }
    public void manageTrips() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press '1' To Add Trip Or '2' To Remove Trip");   //in main
        int i =scanner.nextInt();
        if (i == 1) {
            System.out.print("Entre The Trip Secret Number: ");
            int TripNumber = scanner.nextInt();
            System.out.print("Entre The Type Of The Trip(Internal Or External): ");
            String Type = scanner.next();
            System.out.print("Entre The Source Of The Trip: ");
            String source = scanner.next();
            System.out.print("Entre The Destination Of the Trip(one-way Or round-trip): ");
            String Destination = scanner.next();
            System.out.print("Entre The Number Of Stops: ");
            int stops = scanner.nextInt();
            System.out.print("Entre The Number Of Seats: ");
            int seats = scanner.nextInt();
            System.out.print("Entre The Price: ");
            double price = scanner.nextDouble();
            Trip trip = new Trip(TripNumber, Type, source, Destination, stops, seats, price);
            Trip.addTrip(trip);
        } else if (i == 2) {
            System.out.print("Entre The Number Of Trip To Delete: ");
            int TripNumber = scanner.nextInt();
            Trip.removeTrip(TripNumber, "Trips.txt");
        }
    }
    public void addVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre The Vehicle Type(bus, minibus, limousine): ");
        String type = scanner.next();
        System.out.print("Entre The Vehicle Capacity: ");
        int capacity = scanner.nextInt();
        System.out.print("Entre The Car License Plate: ");
        String plate = scanner.next();
        Vehicle vehicle = new Vehicle(type, capacity, plate);
        Vehicle.addVehicle(vehicle);
    }
    public void addEmployee(String fileName){
       super.register(fileName);
    }
    public void ViewAssignTrips(){
system system=new system();
system.TripsList();
    }
    public void report(){
        System.out.println("The Vehicles In Company: ");
        Vehicle vehicle=new Vehicle();
        vehicle.Display();
        System.out.println();
        System.out.println("The Trips In Company: ");
        system system=new system();
        system.TripsList();
        System.out.println();
        System.out.println("The Mangers In Company: ");
        system.MangersList();
        System.out.println();
        System.out.println("The Drivers In Company: ");
        system.DriverList();
    }
}