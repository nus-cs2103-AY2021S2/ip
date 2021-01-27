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
     * main method which runs the chatbot.
     *
     * @param args empty string array.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

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
