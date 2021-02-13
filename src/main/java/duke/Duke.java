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
     * Constructor for our Dukebot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * The main driver method to test our program. The method terminates with the "bye" command.
     *
     * @param args the user input
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Duke spongebob = new Duke();
        System.out.println(spongebob.greet());
        while (true) {
            String nextCommand = scan.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println(spongebob.exit());
                break;
            }
            try {
                System.out.println(spongebob.process(nextCommand));
            } catch (DukeException exception) {
                System.out.println(exception);
            }
        }
    }

    /**
     * This method will return a message greeting the user.
     *
     * @return a string representation of Duke's greeting
     */
    public String greet() {
        String greetingMessage = "Hello! I'm Spongebob!\nWhat can I do for you?";
        return greetingMessage;
    }
    private String exit() {
        String farewellMessage = "Bye. Hope to see you again soon!";
        return farewellMessage;
    }
    private String process(String input) throws DukeException {
        String[] parsedCommand = this.parser.parseCommand(input);
        return this.ui.processCommand(parsedCommand, this.taskList);
    }
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        try {
            return this.process(input);
        } catch (DukeException exception) {
            return exception.toString();
        }
    }
}
