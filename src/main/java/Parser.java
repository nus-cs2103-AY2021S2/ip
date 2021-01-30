import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private final Scanner in;
    private final PrintStream out;

    /** The number of tasks at the start of the program. */
    public static int totalTasks = 0;
    /** A boolean function to check if the user decides to terminate the program. */
    public boolean canContinue = true;

    public Parser() {
        this(System.in, System.out);
    }

    public Parser(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Process the user input to make sense for the system.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param ui UI structure to show the user correct display.
     */
    public void processInput(ArrayList<Task> tasks, Ui ui) {
        String nextInput = in.nextLine();
        String command = nextInput.contains(" ") ? nextInput.split(" ")[0] : nextInput;
        try {
            if (command.equals("todo")) {
                todo(nextInput, tasks, ui);
            } else if (command.equals("deadline")) {
                deadline(nextInput, tasks, ui);
            } else if (command.equals("event")) {
                event(nextInput, tasks, ui);
            } else if (command.equals("done")) {
                done(nextInput, tasks, totalTasks, ui);
            } else if (command.equals("delete")) {
                delete(nextInput, tasks, totalTasks, ui);
            } else if (command.equals("list")) {
                list(tasks, totalTasks, ui);
            } else if (command.equals("find")) {
                find(nextInput, tasks, totalTasks, ui);
            } else if (command.equals("bye")) {
                byeCommand(ui);
            } else {
                wrongCommand();
            }
        } catch (DukeException e) {
            out.println(e);
        }
    }

    /**
     * Signal termination of the program.
     */
    public void terminate() {
        this.canContinue = false;
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
     * @param ui UI structure to show the user correct display.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public void todo(String nextInput, ArrayList<Task> tasks, Ui ui) throws DukeException{
        if (nextInput.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String command = nextInput.substring(5);
        tasks.add(new Todo(command, false));
        taskAdded();
        ui.showTodoMsg(tasks, totalTasks);
    }

    /**
     * Adds a Deadline task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param ui UI structure to show the user correct display.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public void deadline(String nextInput, ArrayList<Task> tasks, Ui ui) throws DukeException{
        if (nextInput.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!nextInput.contains("/")){
            throw new DukeException("OOPS!!! The date information of a deadline cannot be empty.");
        }
        String command = nextInput.substring(9, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Deadline(command, dateInfo, false, false));
        taskAdded();
        ui.showDeadlineMsg(tasks, totalTasks);
    }

    /**
     * Adds a Event task.
     * @param nextInput The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param ui UI structure to show the user correct display.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public void event(String nextInput, ArrayList<Task> tasks, Ui ui) throws DukeException{
        if (nextInput.length() < 7) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!nextInput.contains("/")) {
            throw new DukeException("OOPS!!! The date information of an event cannot be empty.");
        }
        String command = nextInput.substring(6, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Event(command, dateInfo, false, false));
        taskAdded();
        ui.showEventMsg(tasks, totalTasks);
    }

    /**
     * Mark the task in the given task number as done.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @param ui UI structure to show the user correct display.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public void done(String command, ArrayList<Task> tasks, int count, Ui ui) throws DukeException{
        if (command.length() < 6) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        tasks.get(itemNum - 1).makeDone();
        ui.showDoneMsg(tasks, itemNum);
    }

    /**
     * Delete the task corresponding to the number input by the user.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @param ui UI structure to show the user correct display.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public void delete(String command, ArrayList<Task> tasks, int count, Ui ui) throws DukeException {
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
        taskDeleted();
        ui.showDeleteMsg(taskRemoved, totalTasks);
   }

    /**
     * List out all user inputs in sequence.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param totalTasks The current number of tasks stored inside the Task Arraylist.
     * @param ui UI structure to show the user correct display.
     */
    public void list(ArrayList<Task> tasks, int totalTasks, Ui ui) {
        ui.showListMsg(tasks, totalTasks);
    }

    /**
     * Find tasks matching the keyword in the command given by the user.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param totalTasks The current number of tasks stored inside the Task Arraylist.
     * @param ui UI structure to show the user correct display.
     * @throws DukeException
     */
    public void find(String command, ArrayList<Task> tasks, int totalTasks, Ui ui) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException("OOPS!!! The keyword cannot be empty.");
        }
        String keyword = command.split(" ")[1];
        ui.showFindMsg(keyword, tasks, totalTasks);
    }

    /**
     * Tells the user that the input given is invalid.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public void wrongCommand() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Saying bye to the user when the user decides to quit.
     */
    public void byeCommand(Ui ui) {
        ui.showByeMsg();
        terminate();
    }
}
