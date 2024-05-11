package bts;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Vehicle {
    private String type;
    private  int capacity=0;
    private String licensePlate;
    public Vehicle(){}
    public Vehicle(String type, int capacity, String licensePlate) {
        this.type = type;
        this.capacity = capacity;
        this.licensePlate = licensePlate;
    }
    public void Display() {
        int counter = 0;
        File myObj = new File("Vehicles.txt");
        try {
            Scanner myReader = new Scanner(myObj);
            if (!myReader.hasNextLine()) {
                System.out.println("The file Vehicles.txt is empty.");
                myReader.close();
                return;
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                counter++;
            }
            myReader.close();
            System.out.println("The number of cars in company: " + (counter/3));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void addVehicle(Vehicle vehicle){
        ArrayList<Vehicle>addVehicle=new ArrayList<>();
        addVehicle.add(vehicle);
        WriteInFile("Vehicles.txt",addVehicle,true);
        System.out.println("The Vehicle Is Added Successfully !");
    }
    public static void WriteInFile(String FileName, ArrayList<Vehicle>FileContent,boolean app) {
        try {
            FileWriter myWriter = new FileWriter(FileName,app);
            for (int i = 0; i < FileContent.size(); i++){
                myWriter.write( "The Vehicle Type: " + FileContent.get(i).type + "\nThe Vehicle Plate: " + FileContent.get(i).licensePlate + "\nThe Vehicle Capacity: " + FileContent.get(i).capacity +"\n");

                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}