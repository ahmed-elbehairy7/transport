import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
public class Trip {
    private  String type;
    private String source;
    private String destination;
    private int stops;
    private int seats;
    private double price;
    private int TripNumber;

    public Trip() {}
    public Trip(int TripNumber,String type, String source, String destination, int stops, int seats, double price) {
        this.TripNumber=TripNumber;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;
    }

    public static void addTrip(Trip trip){
        ArrayList<Trip>addTrip=new ArrayList<>();
        addTrip.add(trip);
        WriteInFile("Trips.txt",addTrip,true);
        System.out.println("The Trip Is Added Successfully !");
   }
    public static void removeTrip(int tripNumber, String fileName) {
        File inputFile = new File(fileName);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        boolean isTripFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("The Trip Number: " + tripNumber + ",")) {
                    isTripFound = true;
                } else {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (isTripFound) {
            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temp file.");
            }
        } else {
            System.out.println("Trip number " + tripNumber + " not found!");
            if (!tempFile.delete()) {
                System.out.println("Could not delete the temp file.");
            }
        }
    }
    public static void WriteInFile(String FileName, ArrayList<Trip> FileContent, boolean app) {
        try {
            FileWriter myWriter = new FileWriter(FileName, app);
            for (int i = 0; i < FileContent.size(); i++) {
                myWriter.write("The Trip Number: " + FileContent.get(i).TripNumber +","+ "The Trip Type: " + FileContent.get(i).type +","+ "The Trip Source: " + FileContent.get(i).source +","+ "The Trip destination: " + FileContent.get(i).destination +","+ "The Trip Stops: " + FileContent.get(i).stops +","+ "The Trip Seats: " + FileContent.get(i).seats +","+ "The Trip Price: " + FileContent.get(i).price + "\n");
                if ((i + 1) % 7 == 0) {
                    myWriter.write(", ");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}