public class Ui {

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

    public static void printExitMessage() {
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }

    public static void printInvalidCommandMessage() {
        System.out.println("\nI'm sorry, but I don't know what that means :-(\n");
    }

    public static void printEmptyTodoMessage() {
        System.out.println("\nOOPS!!! The description of a todo cannot be empty.\n");
    }

    public static void printUnableToSaveDataMessage() {
        System.out.println("Sorry, unable to save data to the hard disk.");
    }

    public static void printInvalidDateFormatMessage() {
        System.out.println("\nDate time format is invalid. " +
                "Please enter the date and time in the following format: DD-MM-YYYY HHMM\n");
    }

    public static void printUnableToLoadDataMessage() {
        System.out.println("\nI'm sorry, I am not able to load data.\n");
    }

}


