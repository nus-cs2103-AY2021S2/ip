package duke;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the task list used by the Duke chat bot.
 * It manages all the tasks in the list.
 */
public class TaskList {

    protected ArrayList<Task> collection;

    /**
     * Initialises the class with an empty
     * task list collection for future
     * task-related operations.
     */
    public TaskList() {
        this.collection = new ArrayList<Task>();
    }

    /**
     * Initialises the class with a specified
     * task list collection for future
     * task-related operations.
     */
    public TaskList(ArrayList<Task> collection) {
        this.collection = collection;
    }

    /**
     * Retrieves the exact task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getList() {
        return this.collection;
    }

    /**
     * Retrieves the exact task list in a
     * nicely formatted output.
     *
     * @return Formatted output.
     */
    public String showList() {
        StringBuilder sb = new StringBuilder();

        sb.append("You got a total of " + this.collection.size() + " task(s).\n");
        for (int i = 0; i < collection.size(); i++) {
           sb.append(String.format("\t%d. %s\n", i + 1, collection.get(i)));
        }

        return sb.toString();
    }

    /**
     * Adds the specified task into the task list.
     *
     * @param type Task type.
     * @param args Task arguments.
     * @return Successful result of the operation.
     * @throws DukeException If invalid task type or arguments specified.
     */
    public String addTask(String type, String[] args) throws DukeException {
        // Ensure task description and argument cannot be empty
        if (args[0].equals("")) {
            throw new DukeException("I need a description of your task...");
        } else if (args[1].equals("")) {
            if (type.equals("deadline"))
                throw new DukeException("I need to know when your task ends...");
            if (type.equals("event"))
                throw new DukeException("I need to know the time period of your event...");
        }

        // Add to collection
        try {
            if (type.equals("todo"))
                this.collection.add(new Todo(args[0]));
            else if (type.equals("deadline"))
                this.collection.add(new Deadline(args[0], LocalDate.parse(args[1])));
            else if (type.equals("event"))
                this.collection.add(new Event(args[0], LocalDate.parse(args[1])));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify a proper date... (Format: YYYY-MM-DD)");
        }
        return "Got it, I have added the task '" + args[0] + "' to your collection.";
    }

    /**
     * Marks specified task from the task list as done.
     *
     * @param index Index of the task from the task list.
     * @return Successful result of the operation.
     * @throws DukeException If invalid index specified.
     */
    public String markDone(String index) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(index.split(" ")[0]) - 1;
            boolean status = this.collection.get(itemIdx).markAsDone();
            if (!status)
                throw new IllegalArgumentException();
            return "Task '" + this.collection.get(itemIdx).getDescription() + "' is marked as done.";
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Task had already been marked as done...");
        }
    }

    /**
     * Deletes specified task from the task list
     *
     * @param index Index of the task from the task list.
     * @return Successful result of the operation.
     * @throws DukeException If invalid index specified.
     */
    public String deleteTask(String index) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(index.split(" ")[0]) - 1;
            Task task = this.collection.remove(itemIdx);
            return "Task '" + task.getDescription() + "' has been deleted.";
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        }
    }
}
