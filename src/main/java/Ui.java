/**
 * Handles the output messages for Duke.
 */
public class Ui {
    /**
     * Returns exit message when the user wants to terminate Duke.
     *
     * @returns Exit message.
     */
    public static String printExitMessage() {
        return "\n~ Bye! Come back soon! UwU ~\n";
    }

    /**
     * Returns error message when the command is not recognised.
     *
     * @returns Error message.
     */
    public static String getInvalidCommandMessage() {
        return "\nI'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Returns error message when the description of the todo task is empty.
     *
     * @returns Error message.
     */
    public static String getEmptyTodoMessage() {
        return "\nOOPS!!! The description of a todo cannot be empty.\n";
    }

    /**
     * Returns error message when the description or date of the deadline task is empty.
     *
     * @returns Error message.
     */
    public static String getInvalidDeadlineMessage() {
        return "\nOOPS!!! The description and date fields of a deadline cannot be empty.\n";
    }

    /**
     * Returns error message when the description or date of the event task is empty.
     *
     * @returns Error message.
     */
    public static String getInvalidEventMessage() {
        return "\nOOPS!!! The description and date fields of an event cannot be empty.\n";
    }

    /**
     * Returns error message when the data can't be saved.
     *
     * @returns Error message.
     */
    public static String getUnableToSaveDataMessage() {
        return "Sorry, unable to save data to the hard disk.";
    }

    /**
     * Returns error message when the input format for date and time is invalid.
     *
     * @returns Error message.
     */
    public static String getInvalidDateFormatMessage() {
        return "\nDate time format is invalid. "
                + "Please enter the date and time in the following format: DD-MM-YYYY HHMM\n";
    }

    /**
     * Returns error message when the program is unable to load data from the hard disk.
     *
     * @returns Error message.
     */
    public static String getUnableToLoadDataMessage() {
        return "\nI'm sorry, I am not able to load data.\n";
    }

    /**
     * Returns welcome message when Duke is first run.
     *
     * @returns Welcome message.
     */
    public static String getWelcomeMessage() {
        return "\nHello! I am Zee!\n";
    }

}


