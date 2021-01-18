import java.util.Scanner;

/**
 * The Duke project
 */
public class Duke {
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);

        String username = sc.nextLine();
        nextGreet(username);

        boolean endOfCycle = false;
        Task[] tasks = new Task[100];
        int count = 0;

        while(!endOfCycle) {
            System.out.print(username + ": ");
            String nextCommand = sc.nextLine();
            String[] commandToWords = nextCommand.split(" ");
            try {
                if (nextCommand.equals("bye")) {
                    bye(username);
                    endOfCycle = true;
                } else if (nextCommand.equals("list")) {
                    list(tasks, count);
                } else if (commandToWords[0].equals("done")) {
                    done(nextCommand, tasks, count);
                } else if (commandToWords[0].equals("todo")) {
                    todo(nextCommand, tasks, count);
                    count++;
                } else if (commandToWords[0].equals("deadline")) {
                    deadline(nextCommand, tasks, count);
                    count++;
                } else if (commandToWords[0].equals("event")){
                    event(nextCommand, tasks, count);
                    count++;
                } else {
                    wrongCommand();
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }

        sc.close();
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
     * @param tasks The Task array containing user tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void todo(String nextCommand, Task[] tasks, int count) throws DukeException{
        if (nextCommand.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String command = nextCommand.substring(5);
        tasks[count] = new Todo(command);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks[count].toString());
        System.out.println("Now you have " + count + 1 + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Deadline task.
     * @param nextCommand The description of the task.
     * @param tasks The Task array containing user tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void deadline(String nextCommand, Task[] tasks, int count) throws DukeException{
        if (nextCommand.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!nextCommand.contains("/")){
            throw new DukeException("OOPS!!! The date information of a deadline cannot be empty.");
        }
        String command = nextCommand.substring(9, nextCommand.indexOf("/") - 1);
        String dateInfo = nextCommand.substring(nextCommand.indexOf("/") + 4);
        tasks[count - 1] = new Deadline(command, dateInfo);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks[count - 1].toString());
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds an Event task.
     * @param nextCommand The description of the task.
     * @param tasks The Task array containing user tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void event(String nextCommand, Task[] tasks, int count) throws DukeException{
        if (nextCommand.length() < 7) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!nextCommand.contains("/")) {
            throw new DukeException("OOPS!!! The date information of an event cannot be empty.");
        }
        String command = nextCommand.substring(6, nextCommand.indexOf("/") - 1);
        String dateInfo = nextCommand.substring(nextCommand.indexOf("/") + 4);
        tasks[count - 1] = new Event(command, dateInfo);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks[count - 1].toString());
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * List out all user inputs in sequence.
     * @param tasks An array of tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void list(Task[] tasks, int count) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++){
            int listNum = i + 1;
            System.out.println(listNum + ". " + tasks[i].toString());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Mark the task in the given task number as done.
     * Tell the user that the task has been marked done.
     * @param tasks An array of tasks in sequence, up to 100.
     */
    public static void done(String command, Task[] tasks, int count) throws DukeException{
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > count || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        tasks[itemNum - 1].makeDone();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:\n" + tasks[itemNum - 1].toString());
        System.out.println("----------------------------------------------------------------------------------------");
    }

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
    }
}

