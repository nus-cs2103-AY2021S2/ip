package duke;

/**
 * Class containing methods for printing different messages
 * according to the situation.
 */
public class Ui {
    /**
     * Prints welcome message.
     */
    static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke! \n" +
                "What would you like to do today? \n" +
                "***********************************");
    }

    /**
     * Prints farewell message upon exit.
     */
    static void printBye() {
        System.out.println("Bye! Stay on task!");
    }

    /**
     * Prints text accompanying a "done" command.
     * @param task Task that has been marked as done.
     */
    static void printDone(Task task) {
        System.out.println("Good job! I've marked this task as done:\n    " +
                task + "\n");
    }

    /**
     * Prints text accompanying a "delete" command.
     * @param task Task that has been deleted.
     * @param size Size of remaining taskList.
     */
    static void printDelete(Task task, int size) {
        System.out.println("Alright, I've deleted this task:\n    " +
                task);

        System.out.println("Now you have " + size +
                " task(s) in the list. \n");
    }

    /**
     * Prints text accompanying "add" command.
     * @param task Task that has been added.
     * @param size Size of new taskList.
     */

    static void printAdd(Task task, int size) {
        System.out.println("Alright! I've added this task: \n   " +
                task + "\nNow you have " + size +
                " task(s) in the list. \n");
    }

    /**
     * Prints error message for IndexOutOfBoundsExceptions
     */
    static void printIndexError() {
        System.out.println("Oh no! This task does not exist. D:\n");
    }

    /**
     * Prints error message when date is typed in the wrong format.
     */
    static void printDateFormatError() {
        System.out.println("Oh no! " +
                " Please key in the date in the format YYYY-MM-DD.\n");
    }
}
