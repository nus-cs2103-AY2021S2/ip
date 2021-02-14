package duke;

import java.io.IOException;
import java.util.Scanner;
import duke.exception.EmptyDescription;
import duke.exception.InvalidTypeOfTask;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke program maintains a taskList for user to track tasks.
 * Reads user input tasks(todo, event, deadline).
 * Able to perform add, delete, markasDone tasks.
 */

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Boolean shouldExit = false;

    /**
     * Initialise Duke chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = storage.load();
    }

    /**
     * Start Duke chat services.
     */
    public void execute() {
        ui.greet();
        Scanner s = new Scanner(System.in);

        while (!shouldExit && s.hasNextLine()) {
            try {
                taskList = ui.readCommand(taskList, s);
                storage.save(taskList.getTasks());
                shouldExit = ui.getExit();
            } catch (EmptyDescription e) {
                ui.enclose(e.toString());
            } catch (InvalidTypeOfTask e) {
                ui.enclose(e.toString());
            }
        }
        assert shouldExit == true : "shouldExit boolean false";
        ui.exit();
    }

    /**
     * Initialise scanner.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.execute();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
