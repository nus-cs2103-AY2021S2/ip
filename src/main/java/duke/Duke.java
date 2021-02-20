package duke;
import java.util.Scanner;


/**
 * The main driver class for Dukebot.
 */
public class Duke {
    // Contains the task list, with operations to add/delete tasks.
    private final TaskList taskList;
    // Deals with making sense of the user command.
    private final Parser parser;
    // Deals with interactions with the user.
    private final Ui ui;

    /**
     * Constructor to initialise a new Dukebot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * The main driver method to test our program. The method terminates with
     * the "bye" command.
     *
     * @param args The system input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke spongebob = new Duke();
        System.out.println(spongebob.greet());
        while (true) {
            String nextCommand = scanner.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println(spongebob.exit());
                break;
            }
            System.out.println(spongebob.process(nextCommand));
        }
    }

    /**
     * This method will return a message greeting the user.
     *
     * @return A string representation of the message greeting the user.
     */
    public String greet() {
        String greetingMessage = "Hello! I'm Spongebob!\n";
        greetingMessage += "What can I do for you?";
        return greetingMessage;
    }

    /**
     * This method will return a message bidding farewell to the user.
     *
     * @return A string representation of the message bidding farewell to the
     * user.
     */
    public String exit() {
        String farewellMessage = "Bye. Hope to see you again soon!";
        return farewellMessage;
    }

    /**
     * The main driver method to be used with our graphical interface.
     *
     * @param input The text input from the user.
     * @return A string representation of the corresponding message
     * representing Duke's response.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            this.exit();
        }
        return this.process(input);
    }
    
    private String process(String input) {
        try {
            String[] parsedCommand = this.parser.parseCommand(input);
            return this.ui.processParsedCommand(parsedCommand, this.taskList);
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }
}
