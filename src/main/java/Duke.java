import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the Duke object
     */

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage);
    }

    /**
     * Duke's exit message
     */

    public void exit() throws IOException {
        this.ui.bye();
        this.storage.update(this.taskList);
        System.exit(0);
    }

    /**
     * Run the Duke object
     */

    public void run() throws IOException {
        this.storage.createFile();
        this.ui.greeting();
        Scanner scanner = new Scanner(System.in);
        this.taskList.addTaskFromFile();
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    this.exit();
                } else {
                    Parser parser = new Parser(this.taskList);
                    parser.parseUserCommand(input);
                }

            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
                break;
            }


        }
    }


    public static void main(String[] args) throws IOException {
        new Duke("data/Duke.txt").run();
    }
}
