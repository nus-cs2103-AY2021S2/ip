import java.util.Scanner;

/** Describes the wrapper class for handling UI. */
public class Ui {
    public final String INVALID_ARGUMENT = "Sorry, you have supplied invalid arguments.";
    public final String INVALID_COMMAND = "Sorry, I don't understand the command.";
    public final String ADD_TASK = "I have added the task to the task list!";
    public final String SHOW_TASKS = "Here are your tasks!";
    public final String COMPLETE_TASK = "I have marked that task as done!";
    public final String DELETE_TASK = "I have deleted that task from the task list!";
    public final String FIND_TASKS = "Finding tasks that match your search keyword...";
    private String name;

    /**
     * Returns a UI object with a customised name.
     *
     * @param n The name of the Duke object used in conversation
     */
    public Ui(String n) {
        name = n;
    }

    /**
     * Prints the message supplied with the name prepended.
     *
     * @param message The message to echo
     */
    public void echo(String message) {
        System.out.println(name + ": " + message);
    }

    /**
     * Greets by echoing the greeting message.
     */
    public void greeting() {
        this.echo("Hi, I am " + name + "! How can I help you?");
    }

    /**
     * Says goodbye by echoing the closing message.
     */
    public void closing() {
        this.echo("Goodbye!");
    }

    /**
     * Prompts the user and scans the input to pass to the parser.
     *
     * @param sc The Scanner object to read user input
     * @return A Command object from the parser
     */
    public Command prompt(Scanner sc) {
        String[] input = sc.nextLine().split(" ");
        return Parser.parse(input);
    }
}
