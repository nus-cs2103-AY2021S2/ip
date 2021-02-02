package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to be added.
     * @return Success Message String.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Got it. I've added this task:\n    "
                + task.toString()
                + this.sizeToString();
    }

    /**
     * Deletes a task from the TaskList, given an index.
     *
     * @param taskIndex 1-based index for the task to be deleted.
     * @return Success Mesasge String.
     * @throws DukeException
     */
    public String deleteTask(int taskIndex) throws DukeException {
        if (taskIndex <= 0 || taskIndex > this.tasks.size()) {
            throw new DukeException("A task with this number does not exist.");
        }
        Task deletedTask = tasks.remove(taskIndex - 1);
        return "Noted. I have removed this task:\n    "
                + deletedTask
                + this.sizeToString();
    }

    /**
     * Marks a task in the TaskList as done, given an index
     *
     * @param taskIndex 1-based index for the task to be deleted.
     * @return Success Message String.
     * @throws DukeException
     */
    public String doTask(int taskIndex) throws DukeException {
        if (taskIndex <= 0 || taskIndex > this.tasks.size()) {
            throw new DukeException("A task with this number does not exist.");
        }
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n      "
                + task.toString();
    }

    /**
     * Returns the tasks that are in the TaskList.
     *
     * @return String representing the tasks in the TaskList.
     */
    public String findTask(String phrase) {
        String str = "Here are the matching tasks in your list:\n      ";
        for (Task t: this.tasks) {
            if (t.description.contains(phrase)) {
                str += t.toString() + "\n      ";
            }
        }
        return str.substring(0, str.length() - 7);
    }

    public String listTasks() {
        return this.toString();
    }

    /**
     * Returns the save String representation of a TaskList.
     *
     * @return a String representing a TaskList.
     */
    public String saveTaskListString() {
        String str = "";
        for (Task t: this.tasks) {
            str += t.saveTaskString();
            str += "\n";
        }
        return str;
    }

    /**
     * Returns a String representation of the amount of tasks in the TaskList.
     *
     * @return String representation of the amount of tasks in the TaskList.
     */
    private String sizeToString() {
        return "\n    Now you have " + this.tasks.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        String str = "";
        if (this.tasks.size() == 0) {
            return str;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            str += String.valueOf(i + 1) + ": " + this.tasks.get(i) + "\n    ";
        }

        return str.substring(0, str.length() - 5);
    }
}
