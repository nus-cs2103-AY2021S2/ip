import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * A personal task managing chatbot project.
 */
public class Duke {
    public TaskList tasks;
    public Storage storage;
    public Ui ui;

    Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            this.tasks = new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException fileException) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Main method which serves as the entry point into the chatbot.
     *
     * @param args empty string array.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the chatbot by taking in user input and processing it.
     */
    public void run() {
        this.ui.greetUser();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            try {
                this.ui.echoCommand(input);
                if (input.equals("bye")) {
                    this.ui.farewellUser();
                    break;
                }
                Parser.parseInput(input, this.tasks, this.storage);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
