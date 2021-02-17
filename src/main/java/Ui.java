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
    public static String printInvalidCommandMessage() {
        return "\nI'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Returns error message when the description of the todo task is empty.
     *
     * @returns Error message.
     */
    public static String printEmptyTodoMessage() {
        return "\nOOPS!!! The description of a todo cannot be empty.\n";
    }

    /**
     * Returns error message when the data can't be saved.
     *
     * @returns Error message.
     */
    public static String printUnableToSaveDataMessage() {
        return "Sorry, unable to save data to the hard disk.";
    }

    /**
     * Returns error message when the input format for date and time is invalid.
     *
     * @returns Error message.
     */
    public static String printInvalidDateFormatMessage() {
        return "\nDate time format is invalid. "
                + "Please enter the date and time in the following format: DD-MM-YYYY HHMM\n";
    }

    /**
     * Prints error message when the program is unable to load data from the hard disk.
     *
     * @returns Error message.
     */
    public static String printUnableToLoadDataMessage() {
        return "\nI'm sorry, I am not able to load data.\n";
    }

    public static String printWelcomeMessage() {
        return "\nHello! I am Zee!\n";
    }

}


