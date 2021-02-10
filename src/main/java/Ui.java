package duke;

/**
 * Encapsulates the visuals used in chatting with user.
 */
public class Ui {

    /**
     * Prints a big Duke and greets the user. Prompts user to start input.
     */
    public String greet() {
        return "Hello! I'm Duke :) What can I do for you?";
    }

    /**
     * Prints a line to separate each reply.
     */
    public String separateLine() {
        return "----------------------------------------------------------";
    }

    /**
     * Prints a bye statement before exit.
     */
    public String sayBye() {
        return "Byebye~ Hope to see you again soon!";
    }
}
