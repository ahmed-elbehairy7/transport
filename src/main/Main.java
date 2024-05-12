import java.util.Scanner;

import behindTheScenes.Passenger;
import behindTheScenes.Manager;
import behindTheScenes.Driver;
import gui.MainFrame;
import cli.*;

public class Main {
    public static void main(String[] args) {
        
        if (args.length == 0) {
            cli();
            return;
        }
        if (args[0].equals("-g") || args[0].equals("--gui")) {
            gui();
            return;
        }
        System.out.println("Usage: java Main [--gui] [-cp path] | [-h]\n\n\nOptions:\n-g, --gui\t start the program in gui mode\n-cp,\t<path>\t path to dist folder where Main.class exists\n\n-h, --help\t show help message");
    }

    private static void gui() {

        new MainFrame();
    }
    
    private static void cli() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a user type:\n\n(P) Passenger:\n(M) Manager:\n(D) Driver:\n\n");

        switch (scanner.nextLine().toUpperCase()) {
            case "P":
                Passenger.initiateClass();
                PassengerCli passenger = new PassengerCli();
                passenger.startFlow();
                break;
            case "M":
                Manager.initiateClass();
                ManagerCli manager = new ManagerCli();
                manager.startFlow();
                 break;
            case "D":
                Driver.initiateClass();
                DriverCli driver = new DriverCli();
                driver.startFlow();
                break;
        
            default:
                System.out.println("\nInvalid user type!");
                break;
        }
    }
}