package duke.command;

import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Event command is used when user wants to add an event task.
 */
public class EventCommand extends AddCommand {

    /** Initialises event command with its description. */
    public EventCommand(String description) {
        super(description.replaceAll("event ", "")); // remove keyword used for identifying task type
    }

    /**
     * Executes event command to add event to tasklist.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        try {
            Task t = new Event(this.description.split(" at ")[0],
                    this.description.split(" at ")[1]); // split remaining line into description and datetime
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
