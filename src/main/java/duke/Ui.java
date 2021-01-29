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
    public void showUsage() {
        this.say("Hey! These are the commands available:");
        this.say("\t- usage");
        this.say("\t- list");
        this.say("\t- todo <task_description>");
        this.say("\t- deadline <task_description> /by <date_time>");
        this.say("\t- event <task_description> /at <date_time>");
        this.say("\t- done <task_number>");
        this.say("\t- delete <task_number>");
        this.say("\t- save");
        this.say("\t- bye");
    }

    /**
     * Prints the line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the greetings.
     */
    public void showGreetings() {
        this.showLine();
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|");
        this.showLine();
        this.say("Hey, hello there! I'm Duke, your personal chat bot.");
        this.say("To know more about what I can do, type 'usage'.");
        this.say("So... Is there anything I can do for you today?");
    }

    /**
     * Confirms with the user whether they are
     * sure about exiting the chat bot.
     *
     * @return Outcome of the user decision.
     */
    public boolean toExit() {
        this.say("Are you sure? (Y/N)");
        String confirmation = this.ask();
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
            this.say("Alright, take care. I hope to see you again soon!");
            return true;
        }
        this.say("Hmm... alright I'll stay.");
        return false;
    }

    /**
     * Prints the file load error message.
     */
    public void showLoadingError() {
        this.say("Unable to load file.");
    }

    /**
     * Prints specified error message.
     * Typically messages sent from DukeExceptions.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        this.say(message);
    }

    /**
     * Formats and prints output messages in a
     * more personalised manner and
     * 'chat bot'-like manner.
     *
     * @param message Message to be formatted and displayed.
     * @param newLine Newline at the end of the message.
     */
    public void say(String message, Boolean newLine) {
        System.out.print(">> " + message);
        if (newLine)
            System.out.print("\n");
    }

    /**
     * Formats and prints output messages in a
     * more personalised manner and
     * 'chat bot'-like manner.
     *
     * @param message Message to be formatted and displayed.
     */
    public void say(String message) {
        this.say(message, true);
    }

    /**
     * Retrieves the user input in a
     * more personalised manner and
     * 'chat bot'-like manner.
     *
     * @return User input.
     */
    public String ask() {
        System.out.print("<< ");
        return this.sc.nextLine();
    }
}
