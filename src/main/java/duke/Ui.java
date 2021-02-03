package duke;

import java.util.Scanner;

/**
 * Represents the UI of the Duke chat bot.
 * It manages all the IO operations.
 */
public class Ui {

    protected Scanner sc;

    /**
     * Initialises the IO objects used by the class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the usage menu.
     */
    public String showUsage() {
        String output = "Hey! These are the commands available:";
        output += "\t- usage";
        output += "\t- list";
        output += "\t- todo <task_description>";
        output += "\t- deadline <task_description> /by <date_time>";
        output += "\t- event <task_description> /at <date_time>";
        output += "\t- done <task_number>";
        output += "\t- delete <task_number>";
        output += "\t- save";
        output += "\t- bye";
        return output;
    }

    /**
     * Prints the greetings.
     */
    public String showGreetings() {
        String output = "Hey, hello there! I'm Duke, your personal chat bot.";
        output += "To know more about what I can do, type 'usage'.";
        output += "So... Is there anything I can do for you today?";
        return output;
    }

    /**
     * Prints the file load error message.
     */
    public String showLoadingError() {
        return this.say("Unable to load file.");
    }

    /**
     * Prints specified error message.
     * Typically messages sent from DukeExceptions.
     *
     * @param message Error message.
     */
    public String showError(String message) {
        return this.say(message);
    }

    /**
     * Formats and prints output messages in a
     * more personalised manner and
     * 'chat bot'-like manner.
     *
     * @param message Message to be formatted and displayed.
     */
    public String say(String message) {
        return message;
    }
}
