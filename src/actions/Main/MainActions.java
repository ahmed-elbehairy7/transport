package actions.Main;

import java.util.ArrayList;

import actions.Action;

public class MainActions {
    public static String title = "Transport";
    public static StartPassenger startPassenger = new StartPassenger();
    public static StartManager startManager = new StartManager();
    public static StartDriver startDriver = new StartDriver();

    MainActions() {

    }

    public static ArrayList<Action> actions() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add((Action) startPassenger);
        actions.add((Action) startManager);
        actions.add((Action) startDriver);
        return actions;
    }
}