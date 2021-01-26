package duke.task;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnknownCommandException;
import duke.utils.Command;
import duke.utils.DateTime;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents list of tasks.
 */
public class TaskList {
    /** Lists of tasks. */
    private ArrayList<Task> taskList;

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Updates tasks.
     * @param taskList new tasks.
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list of tasks.
     * @param command Command to determine type of task to add.
     * @param input Input string.
     * @return Task The task that was added.
     * @throws ArrayIndexOutOfBoundsException If input is not complete.
     * @throws InvalidDateException If date is invalid.
     * @throws UnknownCommandException If command is invalid.
     */
    public Task addTask(Command command, String input) throws ArrayIndexOutOfBoundsException, InvalidDateException, UnknownCommandException {
        String[] tokens;
        Task task;

        switch (command) {
            case TODO:
                task = new Todo(input);
                break;
            case DEADLINE:
                tokens = input.split(" /by ", 2);
                input = tokens[0];
                try {
                    task = new Deadline(input, DateTime.parseDate(tokens[1]));
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException(tokens[1]);
                }
                break;
            case EVENT:
                tokens = input.split(" /at ", 2);
                input = tokens[0];
                try {
                    task = new Event(input, DateTime.parseDate(tokens[1]));
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException(tokens[1]);
                }
                break;
            default:
                throw new UnknownCommandException(command.name());
        }

        taskList.add(task);

        return task;
    }

    /**
     * Marks a task as done.
     * @param idx Index of task to mark as done.
     * @return Task that was marked as done.
     * @throws InvalidInputException If index is not in valid range.
     */
    public Task markAsDone(int idx) throws InvalidInputException {
        Task task;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(taskList.size());
        }

        task.markAsDone();

        return task;
    }

    /**
     * Deletes a task.
     * @param idx Index of task to be deleted.
     * @return Task that was deleted.
     * @throws InvalidInputException If index is not in valid range.
     */
    public Task delete(int idx) throws InvalidInputException {
        Task task;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                throw new InvalidInputException();
            } else {
                throw new InvalidInputException(taskList.size());
            }
        }

        taskList.remove(idx);

        return task;
    }

    /**
     * Returns size of list of tasks.
     * @return size of list of tasks.
     */
    public int getSize() {
        return taskList.size();
    }
}
