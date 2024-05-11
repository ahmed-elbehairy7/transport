import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
public class passengers extends User {
    public static void selectTrip() {
        String tripsFilePath = "Trips.txt";
        String passengerTripFilePath = "Ticket.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tripsFilePath));
            List<String> trips = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                trips.add(line);
            }
            reader.close();
            System.out.println("Select Your Trip:");
            for (int i = 0; i < trips.size(); i++) {
                System.out.println((i + 1) + ". " + trips.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose a trip by entering its corresponding number: ");
            int choice = scanner.nextInt();
            System.out.print("For more security enter your Ticket name as you like: ");
            String name=scanner.next();
            if (choice >= 1 && choice <= trips.size()) {
                FileWriter writer = new FileWriter(passengerTripFilePath, true); // Append mode
                writer.write("The Ticket name:"+name+","+trips.get(choice - 1) + "\n");
                writer.close();
                System.out.println("Your trip has been recorded ");
            } else {
                System.out.println("Invalid choice. Please enter a number within the range of available options.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing the files: " + e.getMessage());
        }}
    public void reviewTicket() {
        String ticketsFilePath = "Ticket.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ticketsFilePath));
            String line;
            boolean found = false;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your Ticket name to review: ");
            String tripName = scanner.nextLine();
            while ((line = reader.readLine()) != null) {
                if (line.contains("The Ticket name:" + tripName)) {
                    found = true;
                    System.out.println("your Ticket :");
                    System.out.println(line);
                    break;
                }
            }
            reader.close();
            if (!found) {
                System.out.println("your Ticket not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    public void cancelTicket(String TicketName, String fileName) {
        File inputFile = new File(fileName);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        boolean isTripFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("The Ticket name:" + TicketName)) {
                    isTripFound = true;
                    for (int i = 0; i < 6; i++) {
                        if (reader.readLine() == null) break;
                    }
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
            System.out.println("This trip not found!");
            if (!tempFile.delete()) {
                System.out.println("Could not delete the temp file.");
            }
        }
    }
    public void Info(String userName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Passengers.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 2 && userData[2].trim().equals(userName.trim())) {
                    System.out.println("Passenger Name: " + userData[0].trim());
                    System.out.println("Passenger Email: " + userData[1].trim());
                    System.out.println("Passenger Username: " + userData[2].trim());
                    return;
                }
            }
            System.out.println("User not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
