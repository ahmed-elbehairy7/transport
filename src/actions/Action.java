package actions;

import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;
import functions.stringToVoid;

public class Action {

    public stringAndStringToBooleanToString inputFunc;
    public stringToVoid outputFunc;
    public String prompt;
    public String cliChar;
    
    public Action(String prompt, String cliChar ) {
        this.prompt = prompt;
        this.cliChar = cliChar.toUpperCase();
    }

    public void print(String text) {
        outputFunc.print(text);
    }

    public String input(String prompt, stringToBoolean validator) {
        return inputFunc.input(prompt, validator);
    }

    public void toCliPrompt() {
        System.out.println("(" + this.cliChar + ")  " + this.prompt);
    }

    public void startFlow(stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        this.inputFunc = inputFunction;
        this.outputFunc = outputFunction;
    }
    
    public static Action newAction(boolean gui, String prompt, String cliChar) {
        return new Action(prompt, cliChar);
    }
}
