import java.util.LinkedList;

/**
 * TaskList class for CS2103T iP. Wrapper class for a LinkedList while also routing messages
 * to a Ui object for printing.
 */
public class TaskList {
    private final LinkedList<Task> list;
    private final Ui ui;

    /**
     * Creates a new TaskList object.
     * @param ui Ui object for messages.
     */
    public TaskList(Ui ui) {
        this.list = new LinkedList<>();
        this.ui = ui;
    }

    /**
     * Returns the size of the list.
     * @return Size of list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns task specified.
     * @param i Index of task of interest.
     * @return Task of interest.
     */
    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Deletes task at specified index and prints relevant messages.
     * @param i Task to be deleted.
     */
    public void delete(int i) {
        Task removed = list.remove(i);
        String[] output = new String[3];
        output[0] = "Noted. I've removed this task: ";
        output[1] = "  " + removed;
        output[2] = "Now you have " + list.size() + " tasks in the list.";
        ui.print(output);
    }

    /**
     * Lists all tasks in the list.
     */
    public void listAll() {
        if (list.size() == 0) {
            ui.print("There are currently no tasks! :)");
        } else {
            String[] output = new String[list.size() + 1];
            output[0] = "Here are the tasks in your list:";
            for (int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                String task =  (i + 1) + "." + currTask.toString();
                output[i + 1] = task;
            }
            ui.print(output);
        }
    }

    /**
     * Mark specified task as done.
     * @param i Task of interest.
     */
    public void markDone(int i) {
        Task done = list.get(i);
        done.markDone();
        String[] output = new String[2];
        output[0] = "Nice! I've marked this task as done:";
        output[1] = "  " + done;
        ui.print(output);
    }

    /**
     * Verbose method for adding task to list.
     * @param t Task to be added.
     */
    public void add(Task t) {
        list.add(t);
        String[] output = new String[3];
        output[0] = "Got it. I've added this task:";
        output[1] = "  " + t;
        output[2] = "Now you have " + list.size() + " tasks in the list.";
        ui.print(output);
    }

    /**
     * Used for importing saved tasks, no messages required.
     * @param t Task to be added.
     */
    public void addImport(Task t) {
        list.add(t);
    }
}
