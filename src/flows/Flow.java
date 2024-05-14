package flows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import actions.Action;
import behindTheScenes.User;
import data.InputData;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;
import gui.components.TextArea;

public class Flow {

    public static Scanner scanner = new Scanner(System.in);

    public static stringAndStringToBooleanToString cliInput = (str, func) -> {
        return InputData.cli(scanner, str, func);
    };
    public static stringAndStringToBooleanToString guiInput = (str, func) -> {
        return InputData.gui(str, func);
    };
    public static stringToVoid cliOutput = (str) -> System.out.println(str);
    public stringToVoid guiOutput;
    public ArrayList<?> actions;

    public Flow() {
    }
    public Flow(ArrayList<?> actions) {
        this.actions = actions;
    }
    
    public void startGuiFlow(TextArea outputArea) {
        this.guiOutput = (str) -> outputArea.setText(str);

    }


    public void startCliFlow() {       
        while (true) {
            
            String choosedAction = chooseAction(actions);

            for (byte i = 0; i < actions.size(); i++) {
                Action action = (Action) actions.get(i);
                if (action.cliChar.equals(choosedAction)) {
                    action.startFlow(cliInput, cliOutput, false);

                }
            }
        }
    }
    
    public String chooseAction(ArrayList<?> Actions) {
        System.out.println("Please choose one of the following:\n");

            for (byte i = 0; i < Actions.size(); i++) {
                ((Action)Actions.get(i)).toCliPrompt();
            }

            System.out.print("\n\n");
            String choosedAction = scanner.nextLine().toUpperCase();
            System.out.print("\n\n");
            return choosedAction;
    }

}
