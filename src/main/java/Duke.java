import java.io.IOException;
import java.util.Scanner;

/**
 * Contains main driver class to run the Duke program
 */
public class Duke {
    Storage storage;
    TaskList tasks;
    Ui ui;
    public static String respond;

    public Duke(String filePath) {
        ui = new Ui();
        ui.greet();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.check());
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public Duke() {
        ui = new Ui();
        ui.greet();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.check());
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Scan inputs from the user and then pass it to the parser so that it can be converted into the commands that
     * this program understands.
     *
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     * @throws IOException   If the named file exists but is a rather than a regular file, does not exist but
     *                       cannot be created, or cannot be opened for any other reason
     */
    public void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            Parser.parse(command);
            if (command.equals("bye")) {
                break;
            }
        }
    }

    /**
     * Main driver class of the Duke program.
     *
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     * @throws IOException   If the named file exists but is a directory rather than a regular file,
     *                       does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException, DukeException {
        Parser.parse(input);
        return respond;
    }
}
