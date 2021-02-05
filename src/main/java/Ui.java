package duke;

/**
 * Encapsulates the visuals used in chatting with user.
 */
public class Ui {

    /**
     * Prints a big Duke and greets the user. Prompts user to start input.
     */
    public void greet() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :) What can I do for you?");
        this.separateLine();
    }

    /**
     * Prints a line to separate each reply.
     */
    public void separateLine() {
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Displays intended message to user.
     */
    public void replyWith(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints a bye statement before exit.
     */
    public void sayBye() {
        System.out.println("Byebye~ Hope to see you again soon!");
    }
}
