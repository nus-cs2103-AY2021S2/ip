import command.Command;
import task.TaskManager;
import util.DukeException;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private TaskManager taskManager;

    public Duke(Storage storage) {
        this.storage = storage;
        try {
            this.taskManager = storage.readTaskManager();
        } catch (IOException e) {
            this.taskManager = new TaskManager();
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage("data", "sweh.txt");
        Duke duke = new Duke(storage);
        Ui.printGreeting();
        duke.run();
    }

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

    public boolean decideToQuit(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.isQuitCommand();
        } catch (DukeException e) {
            return false; // Parser did not successfully detect a QuitCommand;
        }
    }

    public String respondToInput(String input) {
        try {
            Command c = Parser.parseCommand(input);
            c.execute(taskManager);
            storage.writeTaskManager(taskManager);
            return c.getMessage();
        } catch (DukeException e) {
            return e.getMsg();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
