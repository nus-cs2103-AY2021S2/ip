package duke;

import java.util.Scanner;

/**
 * Handles the User input and output
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    private static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructor for a Ui.
     */
    public Ui(){
    }

    /**
     * The welcome prompt when Duke is first initialised
     */
    public void welcome(){
        reply("Hello from\n" + logo + "\tHello! I'm Duke\n" + "\tWhat can I do for you?");
    }

    /**
     * Gets the input from the user
     * @return a string comprising of the commands from the user.
     */
    public String getInput(){
        return sc.nextLine();
    }

    /**
     * Prints the error message for a caught CommandException
     * @param message the error message to be printed
     */
    public void showError(String message){
        reply(message);
    }

    /**
     * Replies with indentation and border lines
     * @param reply content of the reply
     */
    public void reply(String reply){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + reply);
        System.out.println("\t____________________________________________________________");
    }
}
