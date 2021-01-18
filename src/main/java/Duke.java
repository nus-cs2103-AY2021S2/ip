import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke project
 */
public class Duke {

    static int totalTasks = 0;
    static boolean endOfCycle = false;

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);

        String username = sc.nextLine();
        nextGreet(username);

        ArrayList<Task> tasks = new ArrayList<>();

        while(!endOfCycle) {
            System.out.print(username + ": ");
            String nextCommand = sc.nextLine();
            String[] commandToWords = nextCommand.split(" ");
            try {
                if (nextCommand.equals("bye")) {
                    bye(username);
                } else if (nextCommand.equals("list")) {
                    list(tasks, totalTasks);
                } else if (commandToWords[0].equals("done")) {
                    done(nextCommand, tasks, totalTasks);
                } else if (commandToWords[0].equals("todo")) {
                    todo(nextCommand, tasks);
                } else if (commandToWords[0].equals("deadline")) {
                    deadline(nextCommand, tasks);
                } else if (commandToWords[0].equals("event")) {
                    event(nextCommand, tasks);
                } else if (commandToWords[0].equals("delete")) {
                    delete(nextCommand, tasks, totalTasks);
                } else {
                    wrongCommand();
                }
            } catch (DukeException e) {
                //noinspection ThrowablePrintedToSystemOut
                System.out.println(e);
            }
        }

        sc.close();
    }

    /**
     * Signal termination of the conversation.
     */
    public static void setEndOfCycle() {
        endOfCycle = true;
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
     * Greets the user when the Duke is launched.
     */
    public static void greet() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Jay!\n" + "What is your name!");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Greets the user again after knowing the user's name.
     * @param username The name of the user.
     */
    public static void nextGreet(String username) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Hi " + username + "!");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Todo task.
     * @param nextCommand The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void todo(String nextCommand, ArrayList<Task> tasks) throws DukeException{
        if (nextCommand.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String command = nextCommand.substring(5);
        tasks.add(new Todo(command));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Deadline task.
     * @param nextCommand The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void deadline(String nextCommand, ArrayList<Task> tasks) throws DukeException{
        if (nextCommand.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!nextCommand.contains("/")){
            throw new DukeException("OOPS!!! The date information of a deadline cannot be empty.");
        }
        String command = nextCommand.substring(9, nextCommand.indexOf("/") - 1);
        String dateInfo = nextCommand.substring(nextCommand.indexOf("/") + 4);
        tasks.add(new Deadline(command, dateInfo));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds an Event task.
     * @param nextCommand The description of the task.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public static void event(String nextCommand, ArrayList<Task> tasks) throws DukeException{
        if (nextCommand.length() < 7) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!nextCommand.contains("/")) {
            throw new DukeException("OOPS!!! The date information of an event cannot be empty.");
        }
        String command = nextCommand.substring(6, nextCommand.indexOf("/") - 1);
        String dateInfo = nextCommand.substring(nextCommand.indexOf("/") + 4);
        tasks.add(new Event(command, dateInfo));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks.get(totalTasks).toString());
        taskAdded();
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

    /**
     * Mark the task in the given task number as done.
     * @param command The command given by user input.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param count The current number of tasks stored inside the Task Arraylist.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public static void done(String command, ArrayList<Task> tasks, int count) throws DukeException{
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
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        String taskRemoved = tasks.get(itemNum - 1).toString();
        tasks.remove(itemNum - 1);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task: \n" + "    " + taskRemoved);
        taskDeleted();
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Tells the user that the input given is invalid.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public static void wrongCommand() throws  DukeException{
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Saying bye to the user when the user decides to quit.
     * @param username The name of the user.
     */
    public static void bye(String username) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Bye " + username + "! Hope to see you again soon!");
        System.out.println("----------------------------------------------------------------------------------------");
        setEndOfCycle();
    }
}

