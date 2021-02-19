package duke;

import java.text.ParseException;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.BadIndexException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;

public class Ui {
    private static final String LOGO =
              "The Wondrous Duke of Singapore(TM)\n"
            + "(wipe your feet)\n";
    private static final String SEPARATOR = "------------------\n";
    private final StringBuilder builder;

    /**
     * Ui constructor
     */
    public Ui() {
        builder = new StringBuilder();
    }

    /**
     * Returns all the accumulated messages while flushing the buffer.
     *
     * @return The message to be printed
     */
    public String flushMessage() {
        String message = builder.toString();
        builder.setLength(0);
        return message;
    }

    /**
     * Prints a start up message for when the program starts.
     */
    public void printStartUp() {
        builder.append(SEPARATOR);
        builder.append("Hello from\n" + LOGO + "\n");
        builder.append("No unicode allowed" + "\n");
        builder.append(SEPARATOR);
    }

    /**
     * Prints a shutdown message for when the program ends.
     */
    public void generateShutDownMessage() {
        builder.append(SEPARATOR);
        builder.append("Goodbye from\n" + LOGO);
        builder.append(SEPARATOR);
    }

    /**
     * Indicate to the user that we are loading a file.
     */
    public void printLoadStart() {
        builder.append("Loading From File...\n");
    }

    /**
     * Indicate to the user that we successfully loaded a file.
     */
    public void printLoadSuccess() {
        builder.append("Loaded\n");
    }

    /**
     * Indicates to the user that the file could not be loaded and that we cannot continue.
     */
    public void printLoadFail() {
        builder.append("Failed to Load file. Aborting.\n");
    }

    /**
     * Dumps the state of the task list visually in a manner suitable for the user
     * to manually copy and save.
     *
     * @param store TaskList that needs to be dumped
     */
    public void dumpState(TaskList store) {
        builder.append("Unable to save list. Dumping ...\n");
        builder.append(store.getList());
        builder.append("Continuing Normal operation\n");
    }

    /**
     * Generate and print message based on command and results from that command
     *
     * @param command The command that has been issued
     * @param data The results of that command, in a pre-processed format
     */
    public void printCommandMessage(Command command, String data) {
        builder.append(SEPARATOR);
        switch (command.getType()) {
        case LIST:
            builder.append(data);
            break;
        case DONE:
            assert data.length() > 0;
            builder.append("The following task is now marked as done:\n"
                    + data + "\n");
            break;
        case ADD:
            assert data.length() > 0;
            builder.append("The following task has been added:\n"
                    + data + "\n");
            break;
        case DELETE:
            assert data.length() > 0;
            builder.append("The following Task has been deleted:\n");
            builder.append(data + "\n");
            break;
        case SEARCH:
            if (data.length() > 0) {
                builder.append("Matching Task(s):\n");
                builder.append(data + "\n");
            } else {
                builder.append("No Matching Task has been found\n");
            }
            break;
        default:
            assert false : "This Really should not happen ever";
            builder.append("ERROR: Unhandled Case!\n");
        }
        builder.append(SEPARATOR);
    }

    /**
     * Generates an UI alert for parsing error
     *
     * @param e Exception that requires an error message
     */
    public void handleParsingError(ParseException e) {
        builder.append("Command has invalid parsing.\n");
        builder.append(e.getMessage() + "\n");
    }
    /**
     * Generates an UI alert for an invalid command.
     *
     * @param e Exception that requires an error message
     */
    public void handleInvalidCommand(InvalidCommandException e) {
        builder.append(e.getMessage() + "\n");
    }
    /**
     * Generates an UI alert for empty arguments.
     *
     * @param e Exception that requires an error message
     */
    public void handleEmptyArgument(EmptyArgumentException e) {
        builder.append("Cannot have empty argument\n");
    }
    /**
     * Generates an UI alert for bad date formatting.
     *
     * @param e Exception that requires an error message
     */
    public void handleBadDate(BadDateArgumentException e) {
        builder.append("Date must be one of the following:\n");
        builder.append("    Special keyword: Yesterday, Today/Now, Tomorrow/Tmr");
        builder.append("    Days of the week; Eg: Mon/M/Mo/Monday");
        builder.append("    'dd MM yyyy'; Eg: 27 08 2044\n");
    }

    /**
     * Generates an UI alert for an out of bounds error
     *
     * @param e Exception that requires an error message
     */
    public void handleBadIndex(BadIndexException e) {
        builder.append(e.getMessage());
    }
}
