import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    ArrayList<Task> list;
    Storage storage;

    public TaskList() throws DukeException{
        this.storage = new Storage();
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


    public void rewriteTasks() throws DukeException {
        storage.clearFile();
        for (Task task : this.list) {
            storage.writeTaskToFile(task);
        }
    }
}
