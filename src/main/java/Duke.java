import main.java.EmptyDescriptionException;
import main.java.InvalidCommandException;
import main.java.LoadTasksException;
import main.java.Parser;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;
import main.java.WriteTasksException;

import java.util.Scanner;

/**
 * A Personal Assistant Chatbot that helps the user to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList tasks;

    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (LoadTasksException e) {
            ui.displayLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            try {
                parser.handleInput(storage, ui, tasks, cmd);
            } catch (EmptyDescriptionException e) {
                ui.displayEmptyDescriptionError();
            } catch (InvalidCommandException e) {
                ui.displayInvalidCommandError();
            } catch (WriteTasksException e) {
                ui.displayWritingError();
            }
            cmd = sc.nextLine();
        }
        sc.close();
        ui.displayExitMsg();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}












