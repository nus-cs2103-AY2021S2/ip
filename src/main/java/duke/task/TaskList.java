package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnknownCommandException;
import duke.utils.Command;

/**
 * Represents list of tasks.
 */
public class TaskList {
    /**
     * Lists of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Updates tasks.
     *
     * @param tasks new tasks.
     */
    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param command Command to determine type of task to add.
     * @param input Input string.
     * @return Task The task that was added.
     * @throws ArrayIndexOutOfBoundsException If input is not complete.
     * @throws InvalidDateException If date is invalid.
     * @throws UnknownCommandException If command is invalid.
     */
    public Task addTask(Command command, String input)
        throws ArrayIndexOutOfBoundsException, InvalidDateException, UnknownCommandException {
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
                task = new Deadline(input, tokens[1]);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException(tokens[1]);
            }
            break;
        case EVENT:
            tokens = input.split(" /at ", 2);
            input = tokens[0];
            try {
                task = new Event(input, tokens[1]);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException(tokens[1]);
            }
            break;
        default:
            throw new UnknownCommandException(command.name());
        }

        tasks.add(task);

        return task;
    }

    /**
     * Marks a task as done.
     *
     * @param idx Index of task to mark as done.
     * @return Task that was marked as done.
     * @throws InvalidInputException If index is not in valid range.
     */
    public Task markAsDone(int idx) throws InvalidInputException {
        Task task;
        try {
            task = tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(tasks.size());
        }

        task.markAsDone();

        return task;
    }

    /**
     * Deletes a task.
     *
     * @param idx Index of task to be deleted.
     * @return Task that was deleted.
     * @throws InvalidInputException If index is not in valid range.
     */
    public Task delete(int idx) throws InvalidInputException {
        Task task;
        try {
            task = tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                throw new InvalidInputException();
            } else {
                throw new InvalidInputException(tasks.size());
            }
        }

        tasks.remove(idx);

        return task;
    }

    /**
     * Returns a list of tasks that matches any of the search parameters.
     *
     * @param searchParameters Strings to compare tasks with.
     * @return List of tasks.
     */
    public ArrayList<Task> getFilteredTaskList(String... searchParameters) {
        ArrayList<Task> filteredArr = new ArrayList<>();

        for (Task task : tasks) {
            String taskDescription = task.description.toLowerCase();
            for (String s : searchParameters) {
                if (taskDescription.contains(s)) {
                    filteredArr.add(task);
                    break;
                }
            }
        }

        return filteredArr;
    }

    /**
     * Returns size of list of tasks.
     *
     * @return size of list of tasks.
     */
    public int getSize() {
        return tasks.size();
    }
}
