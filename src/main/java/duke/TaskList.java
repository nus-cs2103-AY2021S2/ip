package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TaskList {
    ArrayList<Task> list;
    Storage storage;

    public TaskList() throws DukeException {
        this.storage = new Storage();
        Ui.printWithStyle("Reading tasks from file...");
        this.list = storage.readTasksFromFile();
    }

    /**
     * Adds a task to the list of tasks and prints to console the number of tasks in the list.
     * @param task task to be added to list
     */
    public void add(Task task) throws DukeException {
        this.list.add(task);
        storage.writeTaskToFile(task);
        Ui.printWithStyle(new String[] {
                "Got it. I've added this task:",
                "    " + task.toString(),
                "Now you have " + this.list.size() + " tasks in the list."
        });
    }

    /**
     * Marks a task as done and also rewrite all tasks in the storage to reflect this change.
     * @param taskNumber task number of the task to be marked as done.
     * @throws DukeException if failed to rewrite tasks to storage.
     */
    public void done(int taskNumber) throws DukeException {
        this.list.get(taskNumber - 1).done();
        rewriteTasks();
    }

    /**
     * Removes a task from the list and prints to console number of tasks left in the list.
     * @param taskNumber task number of task to be removed.
     */
    public void remove(int taskNumber) throws DukeException {
        Ui.printWithStyle(new String[] {
                "Noted. I've removed this task:",
                this.list.get(taskNumber - 1).toString(),
                "Now you have " + (this.list.size() - 1) + " tasks in the list."
        });
        this.list.remove(taskNumber - 1);
        //Rewrite all tasks
        rewriteTasks();
    }

    /**
     * Prints to console all tasks that are present in the list.
     */
    public void printList() {
        String[] printedArray = new String[this.list.size() + 1];
        printedArray[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.list.size(); i++) {
            String listEntry = String.valueOf(i + 1) + "." +
                    this.list.get(i).toString();
            printedArray[i + 1] = listEntry;
        }
        Ui.printWithStyle(printedArray);
    }

    /**
     * Clears storage file of its contents and rewrites all tasks to storage file.
     * @throws DukeException if failed to clear storage file or failed to write a task to storage file.
     */
    public void rewriteTasks() throws DukeException {
        storage.clearFile();
        for (Task task : this.list) {
            storage.writeTaskToFile(task);
        }
    }

    /**
     * Applies a function to all tasks in the list.
     * @param funct function to be applied.
     */
    public void forEach(Consumer<Task> funct) {
        for (Task task : list) {
            funct.accept(task);
        }
    }

    /**
     * Returns an ArrayList of tasks in which all tasks satisfy the predicate.
     * @param predicate the function used to filter tasks.
     * @return ArrayList of filtered tasks.
     */
    public ArrayList<Task> filter(Predicate<Task> predicate) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task task : list) {
            if (predicate.test(task)) {
                output.add(task);
            }
        }
        return output;
    }
}
