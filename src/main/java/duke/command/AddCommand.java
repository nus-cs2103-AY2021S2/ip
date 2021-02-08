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
 * Handles adding of ToDo, Event and Deadline tasks
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand, for ToDo task
     *
     * @param command ToDo command
     * @param description Task name of ToDo
     */
    public AddCommand(String command, String description) {
        super.command = command;
        super.description = description;
        super.date = "";
    }

    /**
     * Overloaded constructor for AddCommand, for Event and Deadline tasks
     *
     * @param command Event or Deadline command
     * @param description Task name of Event or Deadline
     */
    public AddCommand(String command, String description, String date) {
        super.command = command;
        super.description = description;
        super.date = date;
    }

    private void todoProcess(String taskName, TaskList tasks) {
        Task task = new ToDo(taskName);
        tasks.add(task);
        updateOutput(task, tasks);
    }

    private void eventProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Event(description, LocalDate.parse(date));
        tasks.add(task);
        updateOutput(task, tasks);
    }

    private void deadlineProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Deadline(description, LocalDate.parse(date));
        tasks.add(task);
        updateOutput(task, tasks);
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    @Override
    protected void updateOutput(Task task, TaskList tasks) {
        output = "Got it. I've added this task: \n"
                + task.toString() + getRemainingTasks(tasks);
    }

    /**
     * Adds task to TaskList, saves to storage file and outputs response to terminal
     *
     * @param tasks TaskList
     * @param storage Storage instance
     * @throws DukeException If invalid date given for event or deadline task
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        switch (command) {
        case "todo":
            todoProcess(description, tasks);
            break;
        case "event":
            eventProcess(description, date, tasks);
            break;
        case "deadline":
            deadlineProcess(description, date, tasks);
            break;
        default:
            break;
        }
        storage.save(tasks);
    }

}
