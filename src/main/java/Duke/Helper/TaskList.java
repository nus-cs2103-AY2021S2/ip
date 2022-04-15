package Duke.Helper;

import java.util.ArrayList;
import java.util.Optional;

import Duke.Command.Command;
import Duke.Constant.Constants;
import Duke.Exception.EmptyTaskException;
import Duke.Exception.InvalidIndex;
import Duke.Exception.InvalidTask;
import Duke.Exception.NoSuchCommandException;
import Duke.Task.Task;

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
                    + "  " + task.toString() + "\n"
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
            return Optional.of(parser.parseTodo(command))
                    .map(todo -> {
                        list.add(todo);
                        return Constants.ADD_TASK_SUCCESS
                                + "  " + todo + "\n"
                                + "Now you have " + list.size() + " tasks in the list.";
                    }).get();
        } else if (command.toLowerCase().startsWith(Command.DEADLINE.getAction() + " ")) {
            return Optional.ofNullable(parser.parseDeadline(command))
                    .map(deadline -> {
                        list.add(deadline);
                        return Constants.ADD_TASK_SUCCESS
                                + "  " + deadline + "\n"
                                + "Now you have " + list.size() + " tasks in the list.";
                    }).orElse(Constants.INVALID_DATETIME_FORMAT);
        } else if (command.toLowerCase().startsWith(Command.EVENT.getAction() + " ")) {
            return Optional.ofNullable(parser.parseEvent(command))
                    .map(event -> {
                        list.add(event);
                        return Constants.ADD_TASK_SUCCESS
                                + "  " + event + "\n"
                                + "Now you have " + list.size() + " tasks in the list.";
                    }).orElse(Constants.INVALID_DATETIME_FORMAT);
        } else {
            throw new NoSuchCommandException();
        }
    }

    /**
     * Finds all the tasks that contains the keyword.
     * @param keywords A list of words that need to find.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTask(String... keywords) {
        assert keywords.length != 0 : "There must be at least 1 keyword";
        ArrayList<Task> result = new ArrayList<>();
        for (String keyword : keywords) {
            for (Task task : list) {
                String description = task.getDescription();
                String time = task.getTime();
                if (description.contains(keyword) || time.contains(keyword)) {
                    result.add(task);
                }
            }
        }
        return result;
    }

    /**
     * Statistics about the items: number of tasks for each type and number of done and not done task within that type.
     * @return a 3x2 matrix represent the statistics.
     */
    public int[][] statistics() {
        int[][] result = new int[3][2];
        //td: first row, deadline: second row, event: third row; done: second col, not done: first col
        for (Task task : list) {
            int row = -1;
            int col = task.getStatusNumber();
            if (task.getType().equals("T")) {
                row = 0;
            } else if (task.getType().equals("D")) {
                row = 1;
            } else {
                assert task.getType().equals("E");
                row = 2;
            }
            result[row][col] += 1;
        }
        return result;
    }
}
