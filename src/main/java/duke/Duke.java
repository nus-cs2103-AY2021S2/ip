package duke;
import java.util.Scanner;


/**
 * The main driver class for Duke. The tasklist contains the task list. The Ui deals
 * with interactions with the user. The parser deals with making sense of the user
 * commands.
 */
class Duke {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor for our Dukebot. The Dukebot will always start by first greeting the user
     * whenever it is instantiated.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        System.out.println("Hello! I'm Icebear");
        System.out.println("What can I do for you?");
    }

    /**
     * The main method of our program. The program terminates with the "bye" command.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Duke iceBear = new Duke();
        while (true) {
            String nextCommand = scan.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                try {
                    String[] processedText = iceBear.parser.processCommand(nextCommand);
                    iceBear.process(processedText);
                } catch (DukeException exception) {
                    System.out.println(exception);
                }
            }
        }
    }
    public String[] processCommand(String input) throws DukeException {
        return this.parser.processCommand(input);
    }

    public String process(String[] processedInput) {
        return this.ui.processCommand(processedInput, this.taskList);
    }
}
