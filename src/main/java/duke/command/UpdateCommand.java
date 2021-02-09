package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.Utility;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles updating of tasks without deletion
 */
public class UpdateCommand extends Command {
    private String selectionIndex;

    /**
     * Constructor for UpdateCommand, specifically for todo tasks
     *
     * @param selection Task to be updated within task list
     * @param addCommand New command to be updated to
     * @param description New Description to be updated to
     */
    public UpdateCommand(String selection, String addCommand, String description) {
        selectionIndex = selection;
        this.command = addCommand;
        this.description = description;
        this.date = "";
    }

    /**
     * Overloaded Constructor for UpdateCommand, specifically for Event and Deadline tasks
     *
     * @param selection Task to be updated within task list
     * @param addCommand New command to be updated to
     * @param description New Description to be updated to
     * @param date New date to be updated to
     */
    public UpdateCommand(String selection, String addCommand, String description, String date) {
        selectionIndex = selection;
        command = addCommand;
        this.description = description;
        this.date = date;
    }

    private void updateProcess(String selectionIndex, TaskList tasks) throws DukeException {
        int taskNum = Integer.parseInt(selectionIndex);

        if (command.equals("todo")) {
            tasks.asList().set(taskNum, new ToDo(description));
        }
        if (command.equals("Event")) {
            if (!Utility.isValidDate(date)) {
                throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
            }
            tasks.asList().set(taskNum, new Event(description, LocalDate.parse(date)));
        }
        if (command.equals("Deadline")) {
            if (!Utility.isValidDate(date)) {
                throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
            }
            tasks.asList().set(taskNum, new Deadline(description, LocalDate.parse(date)));
        }

        Task task = tasks.get(taskNum);
        updateOutput(task, tasks);
    }

    @Override
    protected void updateOutput(Task task, TaskList tasks) {
        output = "Nice! I've updated the previous task to the following:\n\t  "
                + task.toString();
    }

    /**
     * Updates a specific task on the TaskList, saves to storage file and outputs response to terminal
     *
     * @param tasks Task being updated
     * @param storage Stores list of task
     * @throws DukeException If invalid date given for event or deadline task
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        updateProcess(selectionIndex, tasks);
        storage.save(tasks);
    }
}
