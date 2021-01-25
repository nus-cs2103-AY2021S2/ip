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
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.readTasksFromFile());
        } catch (FileNotFoundException fileException) {
            tasks = new TaskList();
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

        ui.greetUser();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String input = sc.nextLine();

            try {
                ui.echoCommand(input);
                if (input.equals("bye")) {
                    ui.farewellUser();
                    break;
                }
                Parser.parseInput(input, tasks, storage);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
