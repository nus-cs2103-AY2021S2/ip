package Duke.Helper;

import java.util.ArrayList;

import Duke.Command.Command;
import Duke.Constant.Constants;
import Duke.Exception.EmptyTaskException;
import Duke.Exception.InvalidIndex;
import Duke.Exception.InvalidTask;
import Duke.Exception.NoSuchCommandException;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

/**
 * A wrapper class that contains all available tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private final Parser parser;

    /**
     * This class constructor has 1 parameter: a list containing all available tasks.
     * @param list A list of all available tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.parser = new Parser();
    }

    /**
     * Returns a list containing all available tasks.
     * @return A list containing all available tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Marks a task in the list, which is specified by an index (1-based), as done.
     * @param index A number between 1 and the number of tasks in the list.
     * @return The status of this command whether it succeeds or fails.
     */
    public String finishTask(int index) {
        try {
            Task task = list.get(index - 1);
            task.markAsDone();
            return Constants.MARK_DONE_TASK + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndex("Done", list.size());
        }
    }

    /**
     * Deletes a task from the list, which is specified by an index (1-based).
     * @param index A number between 1 and the number of tasks in the list.
     * @return The status of the deletion whether it succeeds or fails and update the number of tasks in the list
     *          after a successful deletion.
     */
    public String deleteTask(int index) {
        try {
            Task task = list.remove(index - 1);
            return Constants.DELETE_TASK_SUCCESS
                    + task.toString() + "\n"
                    + "Now you have " + list.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndex("Delete", list.size());
        }
    }

    /**
     * Adds a new task to the list and the command must be a Deadline, Event or Todo.
     * @param command A string that contain the information about the task that needs to be added.
     * @return The status of this command it succeeds or fails and update the number of tasks in the list.
     * @throws NoSuchCommandException The command is not of type Deadline, Event or Todo.
     * @throws EmptyTaskException A task with no description.
     * @throws InvalidTask A Deadline or Event command without its signature word ("/by" and "/at" respectively).
     */
    public String addTask(String command) throws NoSuchCommandException, EmptyTaskException, InvalidTask {
        if (command.equalsIgnoreCase(Command.TODO.getAction())
                || command.equalsIgnoreCase(Command.DEADLINE.getAction())
                || command.equalsIgnoreCase(Command.EVENT.getAction())) {
            throw new EmptyTaskException(command);
        } else if (command.toLowerCase().startsWith(Command.TODO.getAction() + " ")) {
            Todo task = parser.parseTodo(command);
            list.add(task);
            return Constants.ADD_TASK_SUCCESS
                    + "  " + task + "\n"
                    + "Now you have " + list.size() + " tasks in the list.";
        } else if (command.toLowerCase().startsWith(Command.DEADLINE.getAction() + " ")) {
            Deadline task = parser.parseDeadline(command);
            if (task == null) {
                return Constants.INVALID_DATETIME_FORMAT;
            } else {
                list.add(task);
                return Constants.ADD_TASK_SUCCESS
                        + "  " + task + "\n"
                        + "Now you have " + list.size() + " tasks in the list.";
            }
        } else if (command.toLowerCase().startsWith(Command.EVENT.getAction() + " ")) {
            Event task = parser.parseEvent(command);
            if (task == null) {
                return Constants.INVALID_DATETIME_FORMAT;
            } else {
                list.add(task);
                return Constants.ADD_TASK_SUCCESS
                        + "  " + task + "\n"
                        + "Now you have " + list.size() + " tasks in the list.";
            }
        } else {
            throw new NoSuchCommandException();
        }
    }

    /**
     * Finds all the tasks that contains the keyword.
     * @param keyword The word that need to find.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : list) {
            String description = task.getDescription();
            String time = task.getTime();
            if (description.contains(keyword) || time.contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
