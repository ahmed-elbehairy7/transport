package flows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import actions.Common.PersonalInfo;
import actions.Common.Quit;
import actions.User.RegActions;
import actions.User.UserAction;
import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;
import behindTheScenes.Vehicle;
import functions.stringAndStringToBooleanToString;
import gui.components.Button;
import gui.components.Frame;
import gui.components.TextArea;

public class UserFlow extends Flow {
    public User user;
    public ArrayList<UserAction> regActions;
    public ArrayList<UserAction> actions;
    public PersonalInfo personalInfo = new PersonalInfo();

    UserFlow(String title, ArrayList<UserAction> actions, boolean gui, String className) {
        super(title);

        initializeActions(actions, className);
        
        if (gui) {
            startGuiFlow(regActions, true);
        }
        else {
            startCliFlow(regActions, true);
        }

        Trip.initiateClass();
        Vehicle.initiateClass();
        Ticket.initiateClass();
    }
    
    public void initializeActions(ArrayList<UserAction> actions, String className) {
        this.regActions = RegActions.actions(className);

        this.actions = new ArrayList<>();
        this.actions.add(personalInfo);
        this.actions.addAll(actions);
    }


    public void startGuiFlow(ArrayList<UserAction> Actions, boolean reg) {
        //Initialize frame
        Frame frame = new Frame(title);
        frame.setLayout(new BorderLayout());
        
        //Button pannel
        int num = Actions.size();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(num, 1));

        JScrollPane scrollPane;
        if (!reg) {
            //Text area and output function
            TextArea outputArea = new TextArea();
            guiOutput = (str) -> outputArea.setText(str);
            scrollPane = new JScrollPane(outputArea);
            frame.add(scrollPane, BorderLayout.CENTER);
        }
        else {
            stringAndStringToBooleanToString temp = guiInput;
            guiInput = (str, func) -> {
                frame.setAlwaysOnTop(false);
                String ans = temp.input(str, func);
                frame.setAlwaysOnTop(true);
                return ans;  
            };
        }

        //For every action in actions
        for (byte i = 0; i < num; i++) {
            //Get the action
            UserAction action = Actions.get(i);
            Button newButton = new Button(action.prompt);

            newButton.addActionListener(e -> {
                user = action.startUserFlow(user, guiInput, guiOutput, true);
                if (!reg) {
                    return;
                }
                if (user == null) {
                    return;
                }
                if (user.success) {
                    frame.dispose();
                }
            });

            buttonPanel.add(newButton);
        }
        if (reg) {
            frame.add(buttonPanel);            
        }
        else {
            frame.add(buttonPanel, BorderLayout.WEST);
        }

        frame.setAlwaysOnTop(reg);
        frame.display();

    }

    public void startCliFlow(ArrayList<UserAction> Actions, boolean reg) {
        Actions.add(new Quit());
        while (true) {
            
            cliOutput.print("\n\n::::" + title + "::::\n\n");
            String choosedAction = chooseCliAction(Actions);

            for (byte i = 0; i < Actions.size(); i++) {
                if (Actions.get(i).cliChar.equals(choosedAction)) {
                    user = Actions.get(i).startUserFlow(user, cliInput, cliOutput, false);
                }
            }

            if (!reg || user == null) {
                cliInput.input("Press enter to continue", e -> true);
                continue;
            }
            if (!user.success) {
                cliOutput.print("\n\nInvalid credentials\n\n");
                continue;
            }
            return;
            
        }
    }

    public void startCliFlow() {
        startCliFlow(actions, false);
    }

    public void startGuiFlow() {
        startGuiFlow(actions, false);
    }
    
}
