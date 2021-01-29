package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;

/**
 * AddCommand represent a command to add a task into the list.
 */
public class AddCommand extends Command{

    /**
     * Returns an AddCommand object that will add a Task into the list.
     *
     * @param command The type of the task (eg Todo, Event, Deadline).
     * @param description Description of the task.
     * @param preposition Preposition used by the user (if applicable).
     * @param date Date of the task (if applicable).
     */
    public AddCommand(String command, String description, String preposition, LocalDate date) {
        super(command, description, preposition, date, false);
    }

    /**
     * Add the correct type of task to the list. Then, outputs a message
     * when the task is successfully added. Save the latest list to the
     * file storing the data.
     *
     * @param tasks TaskList storing the current tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     * @throws DukeException If the current list cannot be saved into the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = getCorrectTask();
        tasks.add(t);
        ui.showAdd(t.toString(), tasks.getSize());
        storage.save(tasks.listOutTaskInString());
    }

    private Task getCorrectTask() {
        switch(command) {
            case "todo":
                return new Todo(description);
            case "event":
                return new Event(description, preposition, date);
            case "deadline":
                return new Deadline(description, preposition, date);
            default:
                return null;
        }
    }
}
