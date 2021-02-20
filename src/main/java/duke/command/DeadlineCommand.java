package duke.command;

import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Deadline command is used for user to add a deadline task.
 */
public class DeadlineCommand extends AddCommand {

    /** Initialises a new deadline command with the task information. */
    public DeadlineCommand(String description) {
        super(description.replaceAll("deadline ", "")); // remove keyword used for identifying task type
    }

    /**
     * Executes deadline command to add deadline task and respond to user.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        try {
            Task t = new Deadline(this.description.split(" by ")[0],
                    this.description.split(" by ")[1]); // split remaining line into description and datetime
            manager.addTask(t);
            this.message += t.toString() + "\n" + String.format("Now you have %s tasks in the list.",
                    manager.taskVolume()); // concatenate reply string
            storage.writeToDisk(manager.getStore()); // store task to harddrive
            return this.message;
        } catch (DateTimeParseException err) {
            return "datetime should be in yyyy-mm-dd format";
        } catch (Exception err) {
            return ":( sorry i don't recognise this format. type help for more info!";
        }
    }
}
