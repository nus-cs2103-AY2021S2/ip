package duke;

import duke.command.Command;
import duke.exceptions.EmptyTaskDukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Handles the core logic of the Duke application
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(new Scanner(System.in));
        // loads the internal storage tasks into task list of program
        this.taskList = storage.load();
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Runs the entire logic for Duke
     */
    public String runEachInput(String fullInput) {
        assert storage != null : "Storage not valid";
        assert taskList != null : "Task List is not instantiated";
        // do something
        try {
            Command c = Parser.parseCommand(fullInput);
            Task task = Parser.parseTask(fullInput, taskList);
            String taskDescription = Parser.parseDescription(fullInput);
            return c.execute(taskDescription, task, taskList, storage);
        } catch (EmptyTaskDukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return e.getMessage()
                    + "\nPlease enter a valid task number.";
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage()
                    + "\nPlease enter a valid task number.";
        } catch (DateTimeParseException e) {
            return e.getMessage()
                    + "\nPlease enter the date in the correct format.";
        }
    }
}
