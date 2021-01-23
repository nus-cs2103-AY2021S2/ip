import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks and prints to console the number of tasks in the list.
     * @param task
     */
    public void add(Task task) {
        this.list.add(task);
        Printer.printWithStyle(new String[] {
                "Got it. I've added this task:",
                "    " + task.toString(),
                "Now you have " + this.list.size() + " tasks in the list."
        });
    }

    public void done(int taskNumber) {
        this.list.get(taskNumber - 1).done();
    }

    /**
     * Removes a task from the list and prints to console number of tasks left in the list.
     * @param taskNumber
     */
    public void remove(int taskNumber) {

        Printer.printWithStyle(new String[] {
                "Noted. I've removed this task:",
                this.list.get(taskNumber - 1).toString(),
                "Now you have " + (this.list.size() - 1) + " tasks in the list."
        });
        this.list.remove(taskNumber - 1);
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
        Printer.printWithStyle(printedArray);
    }
}
