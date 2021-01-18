import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>(100);

    private static String[] commands = { "bye", "list", "done", "todo", "deadline", "event"};

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

    // Adds Task to ArrayList.
    public void addTask(Task t) {
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

    // Marks Task at index number to the desired boolean value.
    public void setTask(int index, boolean b) {
        Task targetTask = tasks.get(index - 1);
        targetTask.setDone(b);
    }

    // Returns the number of tasks in the ArrayList.
    public int getTasksSize() {
        return tasks.size();
    }

    public void checkInput(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        for (String command : commands) {
            if (splittedInput[0].equals(command)) {
                break;
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that " +
                        "means :-(");
            }
        }
    }

    public void checkArgument(String task, String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + task +
                    " cannot be empty.");
        }
    }
}
