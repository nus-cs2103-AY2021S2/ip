import java.io.*;
import java.util.ArrayList;

/**
 * The Duke project.
 */
public class Duke {

    /** The number of tasks at the start of the program. */
    static int totalTasks = 0;

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    public void run() throws IOException {
        ui.welcomeMsg();
        ui.nameMsg();
        ArrayList<Task> tasks = new ArrayList<>();
        storage.readOrCreateFile(tasks);
        while(parser.canContinue) {
            ui.prompt();
            parser.processInput(tasks, totalTasks, ui);
        }
        storage.writeListIntoFile(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Increase the total number of tasks in the list by 1.
     */
    public static void taskAdded() {
        totalTasks++;
    }

    /**
     * Decrease the total number of tasks in the list by 1.
     */
    public static void taskDeleted() {
        totalTasks--;
    }

    /**
     * Adds a Todo task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void todo(String nextInput, ArrayList<Task> tasks) throws DukeException{
        if (nextInput.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String command = nextInput.substring(5);
        tasks.add(new Todo(command, false));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Deadline task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void deadline(String nextInput, ArrayList<Task> tasks) throws DukeException{
        if (nextInput.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!nextInput.contains("/")){
            throw new DukeException("OOPS!!! The date information of a deadline cannot be empty.");
        }
        String command = nextInput.substring(9, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Deadline(command, dateInfo, false, false));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds an Event task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void event(String nextInput, ArrayList<Task> tasks) throws DukeException{
        if (nextInput.length() < 7) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!nextInput.contains("/")) {
            throw new DukeException("OOPS!!! The date information of an event cannot be empty.");
        }
        String command = nextInput.substring(6, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Event(command, dateInfo, false, false));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Mark the task in the given task number as done.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public static void done(String command, ArrayList<Task> tasks, int count) throws DukeException{
        if (command.length() < 6) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        tasks.get(itemNum - 1).makeDone();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(itemNum - 1).toString());
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Delete the task corresponding to the number input by the user.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public static void delete(String command, ArrayList<Task> tasks, int count) throws DukeException {
        if (command.length() < 8) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        String taskRemoved = tasks.get(itemNum - 1).toString();
        tasks.remove(itemNum - 1);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task:\n" + "    " + taskRemoved);
        taskDeleted();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * List out all user inputs in sequence.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     */
    public static void list(ArrayList<Task> tasks, int count) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++){
            int listNum = i + 1;
            System.out.println(listNum + ". " + tasks.get(i).toString());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
}

