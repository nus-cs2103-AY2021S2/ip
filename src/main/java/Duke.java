import command.Command;
import task.TaskManager;
import util.DukeException;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Primary logic class for containing the application. Relies on a Storage object
 * to store persistent user information.
 */
public class Duke {
    private final Storage storage;
    private TaskManager taskManager;

    /**
     * Constructor to initialise a new Duke instance. Will create a brand new TaskManager
     * instance if the supplied Storage object does not point to an existing save file or
     * if the existing save file cannot be read.
     *
     * @param storage Storage object to load persistent TaskManager information from.
     */
    public Duke(Storage storage) {
        this.storage = storage;
        try {
            this.taskManager = storage.readTaskManager();
        } catch (IOException e) {
            Ui.printOutput(e.getMessage());
            this.taskManager = new TaskManager();
        }
    }

    /**
     * Entry point for command-line interface for Duke.
     *
     * @param args Arguments for the execution of Duke. Should be left blank.
     */
    public static void main(String[] args) {
        Storage storage = new Storage("data", "sweh.txt");
        Duke duke = new Duke(storage);
        Ui.printGreeting();
        duke.run();
    }

    /**
     * Called by the CLI instance of Duke in the main() method.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String message = respondToInput(userInput);
            Ui.printOutput(message);

            boolean shouldQuit = decideToQuit(userInput);

            if (shouldQuit) {
                break;
            }
        }
    }

    /**
     * Determines if the user entered a quit command in their input.
     *
     * @param input Text inputted by the user.
     * @return True, if the user inputted a quit command.
     */
    public boolean decideToQuit(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.isQuitCommand();
        } catch (DukeException e) {
            return false; // Parser did not successfully detect a QuitCommand;
        }
    }

    /**
     * Responds to the user's inputs by manipulating the TaskManager instance,
     * saves the new state of the TaskManager to the save file, and returns a
     * String describing the action that was performed.
     *
     * @param input Text inputted by the user.
     * @return String description of the actions performed by the Duke instance.
     */
    public String respondToInput(String input) {
        try {
            Command c = Parser.parseCommand(input);
            String response = c.execute(taskManager);
            storage.writeTaskManager(taskManager);
            return response;
        } catch (DukeException e) {
            return e.getMsg();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
