package duke;

import java.text.ParseException;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;

public class Ui {
    private static final String LOGO =
              " ____        _        \n" //TODO: Figure out if this is allowed by style
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String SEPARATOR = "------------------\n";

    /**
     * Prints a start up message for when the program starts.
     */
    public void printStartUp() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("No unicode allowed");
    }

    /**
     * Prints a shutdown message for when the program ends.
     */
    public void printShutDown() {
        System.out.println(SEPARATOR + "Goodbye from\n" + LOGO);
    }

    /**
     * Prints a prompt to indicate that we are expecting input.
     */
    public void printPrompt() {
        System.out.print(SEPARATOR + "Listening to your input: ");
    }

    /**
     * Indicate to the user that we are loading a file.
     */
    public void printLoadStart() {
        System.out.println("Loading From File...");
    }

    /**
     * Indicate to the user that we successfully loaded a file.
     */
    public void printLoadSuccess() {
        System.out.println("Loaded");
    }

    /**
     * Indicates to the user that the file could not be loaded and that we cannot continue.
     */
    public void printLoadFail() {
        System.out.println("Failed to Load file. Aborting.");
    }

    /**
     * Dumps the state of the task list visually in a manner suitable for the user
     * to manually copy and save.
     *
     * @param store TaskList that needs to be dumped
     */
    public void dumpState(TaskList store) {
        System.out.println("Unable to save list. Dumping ...");
        System.out.print(store.getList());
        System.out.println("Continuing Normal operation");
    }

    /**
     * Generate and print message based on command and results from that command
     *
     * @param command The command that has been issued
     * @param data The results of that command, in a pre-processed format
     */
    public void printCommandMessage(Command command, String data) {
        switch (command.getType()) {
        case LIST:
            System.out.print(data);
            break;
        case DONE:
            System.out.println("The following task is now marked as done:\n"
                    + data);
            break;
        case ADD:
            System.out.println("The following task has been added:\n"
                    + data);
            break;
        case DELETE:
            System.out.println("The following Task has been deleted:");
            System.out.println(data);
            break;
        case SEARCH:
            if (data.length() > 0) {
                System.out.println("Matching Task(s):");
                System.out.println(data);
            } else {
                System.out.println("No Matching Task has been found");
            }
            break;
        default:
            System.out.println("ERROR: Unhandled Case!");
        }
    }

    /**
     * Generates an UI alert for some particular error.
     *
     * @param e Exception that requires an error message
     */
    public void handleException(ParseException e) {
        System.out.println("Command has invalid parsing.");
        System.out.println(e.getMessage());
    }
    /**
     * Generates an UI alert for some particular error.
     *
     * @param e Exception that requires an error message
     */
    public void handleException(InvalidCommandException e) {
        System.out.println(e.getMessage());
    }
    /**
     * Generates an UI alert for some particular error.
     *
     * @param e Exception that requires an error message
     */
    public void handleException(EmptyArgumentException e) {
        System.out.println("Cannot have empty argument");
        System.out.println(e.getMessage());
    }
    /**
     * Generates an UI alert for some particular error.
     *
     * @param e Exception that requires an error message
     */ //TODO: Figure out how to do javadoc for overloaded method.
    public void handleException(BadDateArgumentException e) {
        System.out.println("Date must be of format 'dd MM yyyy'; Eg: 27 08 2044");
        System.out.println(e.getMessage());
    }
}
