package duke;

import java.util.Scanner;

/**
 * Represents a user interface that interacts with the user.
 */
public class Ui {

    /**
     * Shows the introductory information.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("So here are the rules!: ");
        System.out.println("-- list:  Prints current tasks in the list\n"
                + "-- todo <nameOfToDoTask>:  Adds a todo task to your list\n"
                + "-- deadline <nameOfDeadlineTask> /by <YYYY-MM-DD HH:MM>:  Adds a deadline task to your list\n"
                + "-- event <nameOfEventTask> /at <YYYY-MM-DD HH:MM>:  Adds an event task to your list\n"
                + "-- delete <taskNumber>:  Deletes specified task\n"
                + "-- done <taskNumber>:  Marks specified task as done\n"
                + "-- taskdate <YYYY-MM-DD>:  Prints tasks in your list that match the specified date\n"
                + "-- find <keyword>:  Prints tasks in your list that match the keyword\n"
                + "-- bye:  Exits Duke");
    }

    /**
     * Shows the exit information.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows a line break.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows error messages.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads user's command.
     *
     * @return User's full command.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}