import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }
    public void add(Task task) {
        this.list.add(task);
        Duke.printWithStyle(new String[] {
                "Got it. I've added this task:",
                "    " + task.toString(),
                "Now you have " + this.list.size() + " tasks in the list."
        });
    }

    public void done(int taskNumber) {
        this.list.get(taskNumber - 1).done();
    }

    public void printList() {
        String[] printedArray = new String[this.list.size() + 1];
        printedArray[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.list.size(); i++) {
            String listEntry = String.valueOf(i + 1) + "." +
                    this.list.get(i).toString();
            printedArray[i + 1] = listEntry;
        }
        Duke.printWithStyle(printedArray);
    }
}
