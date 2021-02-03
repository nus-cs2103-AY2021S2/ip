public class Ui {

    /**
     * Prints welcome message when the user runs Duke.
     */
    public static void printWelcomeMessage() {
        String logo =
                "     ______\n" +
                        "    |___  /\n" +
                        "       / / \n" +
                        "      / /  \n" +
                        "     / /__ \n" +
                        "    /_____|\n";
        System.out.println("\n~ Hello! I am Zee :) ~\n"
                + logo + "\n"
                + "~ What can I do for you today? ~\n");
    }

    /**
     * Prints exit message when the user wants to terminate Duke.
     */
    public static String printExitMessage() {
        return "\n~ Bye! Come back soon! UwU ~\n";
    }

    /**
     * Prints error message when the command is not recognised.
     */
    public static String printInvalidCommandMessage() {
        return "\nI'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Prints error message when the description of the todo task is empty.
     */
    public static String printEmptyTodoMessage() {
        return "\nOOPS!!! The description of a todo cannot be empty.\n";
    }

    /**
     * Prints error message when the data can't be saved.
     */
    public static String printUnableToSaveDataMessage() {
        return "Sorry, unable to save data to the hard disk.";
    }

    /**
     * Prints error message when the input format for date and time is invalid.
     */
    public static String printInvalidDateFormatMessage() {
        return "\nDate time format is invalid. " +
                "Please enter the date and time in the following format: DD-MM-YYYY HHMM\n";
    }

    /**
     * Prints error message when the program is unable to load data from the hard disk.
     */
    public static String printUnableToLoadDataMessage() {
        return "\nI'm sorry, I am not able to load data.\n";
    }

}


