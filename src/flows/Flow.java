package flows;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.GridLayout;

import actions.Action;
import actions.Common.Quit;
import data.InputData;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;
import gui.components.Button;
import gui.components.Frame;

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
    public String title;

    public Flow(String title) {
        this.title = title;
    }

    public Flow(String title, ArrayList<?> actions) {
        this.title = title;
        this.actions = actions;
    }
    
    public void startGuiFlow() {
        //Initialize frame
        Frame frame = new Frame(title);
        int num = actions.size();
        frame.setLayout(new GridLayout(num, 1));

        //For every action in actions
        for (byte i = 0; i < num; i++) {
            //Get the action
            Action action = (Action) actions.get(i);
            Button newButton = new Button(action.prompt);

            newButton.addActionListener(e -> {
                frame.dispose();
                action.startFlow(cliInput, cliOutput, true);
            });
            
            frame.add(newButton);
        }
        frame.display();
    }


    @SuppressWarnings("unchecked")
    public void startCliFlow() {

        ((ArrayList<Action>) actions).add(new Quit());
        
        while (true) {

            cliOutput.print("\n\n::::" + title + "::::\n");
            
            String choosedAction = chooseCliAction(actions);

            for (byte i = 0; i < actions.size(); i++) {
                Action action = (Action) actions.get(i);
                if (action.cliChar.equals(choosedAction)) {
                    action.startFlow(cliInput, cliOutput, false);

                }
            }

            cliInput.input("Press enter to continue", e -> true);
        }
    }
    
    public String chooseCliAction(ArrayList<?> Actions) {
        cliOutput.print("Please choose one of the following:\n");

            for (byte i = 0; i < Actions.size(); i++) {
                ((Action)Actions.get(i)).toCliPrompt();
            }

            cliOutput.print("\n\n");
            String choosedAction = scanner.nextLine().toUpperCase();
            cliOutput.print("\n\n");
            return choosedAction;
    }

}
