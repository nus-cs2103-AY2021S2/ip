package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle all actions regarding tasks.
 */
public class TaskList {
    private List<Task> lst;
    private Storage storage;

    /**
     * Construct a list of tasks.
     */
    TaskList() {
        this.storage = new Storage("duke.txt");
        this.lst = storage.read();

    }

    private void addToDo(String desc) throws DukeException {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        lst.add(new ToDo(desc));
    }

    private void addDeadline(String desc) throws DukeException {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        int index = desc.indexOf('/');
        lst.add(new Deadline(desc.substring(0, index - 1), desc.substring(index + 4)));
    }

    private void addEvent(String desc) throws DukeException {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        int index = desc.indexOf('/');
        lst.add(new Event(desc.substring(0, index - 1), desc.substring(index + 4)));
    }

    /**
     * Add a tasks to the list.
     * @param command the task description
     */
    public void addTask(String command) {
        try {
            if (command.startsWith("todo")) {
                addToDo(command.substring(4));
            } else if (command.startsWith("deadline")) {
                addDeadline(command.substring(8));
            } else if (command.startsWith("event")) {
                addEvent(command.substring(5));
            } else {
                throw new DukeException("I have no idea.");
            }
            Ui.addTask(lst);
            storage.write(lst);
        } catch (DukeException err) {
            Ui.printException(err);
        }

    }

    /**
     * Mark a task at the specified index as done.
     * @param position the task index
     */
    public void markDone(String position) {
        int index = Integer.parseInt(position) - 1;
        lst.get(index).markDone();
        Ui.markDone(lst.get(index));
        storage.write(lst);
    }

    /**
     * Deleting a task at the specified index.
     * @param position the task index
     */
    public void deleteTask(String position) {
        int index = Integer.parseInt(position) - 1;
        Task task = lst.remove(index);
        Ui.delete(lst, task);
        storage.write(lst);
    }

    /**
     * List the tasks.
     */
    public void listTask() {
        Ui.list(lst);
    }

    /**
     * Finds a task that matches a keyword.
     * @param keyword the keyword to be searched
     */
    public void findTask(String keyword) {
        List<Task> res = new ArrayList<>();
        for (Task t: lst) {
            if (t.getTask().contains(keyword)) {
                res.add(t);
            }
        }
        Ui.list(res);
    }
}
