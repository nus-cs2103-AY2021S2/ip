package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 *
 * @author Benedict Khoo
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Attempts to construct a task list by parsing the input string. Throws a TaskParseException if parsing fails.
     *
     * @param serialized The task list in serialized form.
     * @throws TaskParseException If parsing fails.
     */
    public TaskList(String serialized) throws TaskParseException {
        tasks = deserialize(serialized);
    }

    private static List<Task> deserialize(String serialized) throws TaskParseException {
        List<Task> ts = new ArrayList<>();

        Scanner sc = new Scanner(serialized);
        while (sc.hasNextLine()) {
            String taskSerial = sc.nextLine();

            Task t;
            if (ToDo.isToDo(taskSerial)) {
                t = ToDo.deserialize(taskSerial);
            } else if (Event.isEvent(taskSerial)) {
                t = Event.deserialize(taskSerial);
            } else if (Deadline.isDeadline(taskSerial)) {
                t = Deadline.deserialize(taskSerial);
            } else {
                throw new TaskParseException("Warning: invalid type. Aborting!");
            }

            ts.add(t);
        }

        return ts;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The task index.
     * @return The task at index.
     */
    public Task getAt(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index The index of the task to remove.
     */
    public void removeAt(int index) {
        tasks.remove(index);
    }

    /**
     * Returns a serialized string of this task list.
     *
     * @return A serialized string of this task list.
     */
    public String serialize() {
        return tasks.stream()
                .map(task -> task.serialize() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return The number of tasks in this list.
     */
    public int taskCount() {
        return tasks.size();
    }
}
