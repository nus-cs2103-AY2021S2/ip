package mainclasses;

import java.util.Scanner;

import exception.DukeException;

public class Ui {
    private Scanner sc;
    private Parser parser;

    /**
     * UI class
     * @param parser
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Allows user to enter string text into the command line
     */
    public String executeInput(String userInput) {
        String output = "";
        try {
            parser.checkInput(userInput);
            output = parser.determineAction(userInput);
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
        return output;
    }

    /**
     * Initialise the first dialog from Duke
     */
    public String initialStatements() {
        return ("Hello! I'm Benny \n") + ("What can I do for you?");
    }


}
