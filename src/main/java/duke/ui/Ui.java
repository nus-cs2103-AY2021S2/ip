package duke.ui;

/**
 * Encapsulates the visuals used in chatting with user.
 */
public class Ui {

    /**
     * Prints a big Duke and greets the user. Prompts user to start input.
     */
    public String greet() {
        return "Hello hello ;> How can I help you?";
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
        return "Byebye~ See you again soon!";
    }
}
