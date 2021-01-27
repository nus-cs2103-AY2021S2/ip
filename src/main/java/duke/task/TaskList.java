package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.common.DukeException;
import duke.common.DukeString;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private TaskList(List<Task> list) {
        this.tasks = list;
    }

    /**
     * Constructs a new TaskList with the given input read from storage.
     * @param input the serialised save file as read from storage
     * @return a new TaskList populated with the Tasks as read from storage
     * @throws DukeException.StorageReadError if the serialised save file is malformed
     */
    public static TaskList deserialise(Scanner input) throws DukeException.StorageReadError {
        List<Task> list = new ArrayList<>();

        String line;
        while (input.hasNextLine()) {
            line = input.nextLine();
            if (line.equals("\n")) {
                break;
            }
            switch (line.split("\255")[0]) {
            case DukeString.COMMAND_DEADLINE :
                list.add(DeadlineTask.deserialise(line));
                break;
            case DukeString.COMMAND_EVENT :
                list.add(EventTask.deserialise(line));
                break;
            case DukeString.COMMAND_TODO :
                list.add(TodoTask.deserialise(line));
                break;
            default:
                throw new DukeException.StorageReadError();
            }
        }

        return new TaskList(list);
    }

    /**
     * Adds the given task to the list.
     * @param task the task to be added to the list
     */
    public void addTask(final Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at the given index as done.
     * @param idx the index of the task to be marked as done
     * @return the String format of the specified task
     */
    public String doneTask(final int idx) {
        tasks.get(idx - 1).markDone();
        return tasks.get(idx - 1).toString();
    }

    /**
     * Deletes the task at the given index.
     * @param idx the index of the task to be deleted
     * @return the String format of the deleted task
     */
    public String deleteTask(final int idx) {
        return tasks.remove(idx - 1).toString();
    }

    /**
     * Getter for the size of the list.
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Serialises the tasks in the list, each task on a newline.
     * @return the serialised version of the tasks in the list
     */
    public String serialise() {
        StringBuilder out = new StringBuilder();
        for (Task task : tasks) {
            out.append(task.serialise());
            out.append('\n');
        }

        return out.toString();
    }

    /**
     * Finds tasks that contain the given string, and outputs a formatted String with the tasks.
     * @param str the string to search for
     * @return a formatted version of the tasks
     */
    public String tasksContaining(String str) {
        StringBuilder out = new StringBuilder();
        int i = 1;

        for (Task task : tasks) {
            if (task.containsTerm(str)) {
                out.append(i++);
                out.append(". ");
                out.append(task.toString());
                out.append('\n');
            }
        }

        if (out.length() == 0) {
            out.append(DukeString.MESSAGE_NONE_FOUND);
        } else {
            out.deleteCharAt(out.length() - 1);
        }

        return out.toString();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            out.append(i + 1);
            out.append(". ");
            out.append(tasks.get(i));

            if (i != tasks.size() - 1) {
                out.append('\n');
            }
        }

        return out.toString();
    }
}
