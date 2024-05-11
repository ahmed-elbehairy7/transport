package bts;
import java.io.*;
import java.util.Scanner;
public class system {
    protected String Username;
    protected String password;
public system(){
}
    public system(String username, String password) {
        Username = username;
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }
    public void readCredentialsFromFile(String filename) {       //to send the username and pass to user class it called in main at first
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[2].equals(Username)&&userData[3].equals(password)) {
                    this.Username = userData[2];
                   this.password = userData[3];
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public void ReadFiles(String fileName){
    try
    {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    } catch(
    FileNotFoundException e)
    {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}
public void TripsList(){
        ReadFiles("Trips.txt");
}
    public void MangersList(){ReadFiles("Manger.txt");}
    public void DriverList(){ReadFiles("Driver.txt");}
}