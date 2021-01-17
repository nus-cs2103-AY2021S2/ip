import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>(100);

    // Divider for Duke's Hello message.
    private static String BORDER = "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-"
            + "+-+-+-+-+-+";

    // Prints a string with a 4-space indent.
    public void indentedPrint(String s) {
        System.out.println("    " + s);
    }

    public void indentedPrint(int i) {
        System.out.println("    " + i);
    }

    // Prints the divider.
    public void printDivider() {
        indentedPrint(BORDER);
    }

    // Greeting message from Duke.
    public void greet() {
        System.out.println(BORDER);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        System.out.println("How may I help you?");
    }

    // Store input string in ArrayList.
    public void addTask(String s) {
        Task t = new Task(s);
        tasks.add(t);
    }

    // Iterates through ArrayList and prints each element.
    public void iterateList() {
        for (int i = 0; i < tasks.size(); i++) {
            this.indentedPrint((i + 1) + ". " + tasks.get(i));
        }
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    // Marks task at index number to the desired boolean value.
    public void setTask(int index, boolean b) {
        Task targetTask = tasks.get(index - 1);
        targetTask.setDone(b);
    }
}
