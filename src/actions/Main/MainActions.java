package actions.Main;

import java.util.ArrayList;

import actions.Action;
import actions.Common.Quit;

public class MainActions {
    public static StartPassenger startPassenger = new StartPassenger();
    public static StartManager startManager = new StartManager();
    public static StartDriver startDriver = new StartDriver();
    public static Quit quit = new Quit();

    MainActions() {

    }

    public static ArrayList<Action> actions() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add((Action) startPassenger);
        actions.add((Action) startManager);
        actions.add((Action) startDriver);
        actions.add((Action) quit);
        return actions;
    }
}