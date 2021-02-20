package duke;

public class Ui {

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

    static void printBye() {
        System.out.println("Bye! Stay on task!");
    }

    static void printDone(Task task) {
        System.out.println("Good job! I've marked this task as done:\n    " +
                task + "\n");
    }

    static void printDelete(Task task, int size) {
        System.out.println("Alright, I've deleted this task:\n    " +
                task);

        System.out.println("Now you have " + size +
                " task(s) in the list. \n");
    }

    static void printAdd(Task task, int size) {
        System.out.println("Alright! I've added this task: \n   " +
                task + "\nNow you have " + size +
                " task(s) in the list. \n");
    }

    static void printIndexError() {
        System.out.println("Oh no! This task does not exist. D:\n");
    }

    static void printDateFormatError() {
        System.out.println("Oh no! " +
                " Please key in the date in the format YYYY-MM-DD.\n");
    }
}
