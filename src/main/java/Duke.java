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
            String dateInfo = nextCommand.substring(nextCommand.indexOf("/") + 4);
            if (nextCommand.equals("bye")) {
                bye(username);
                endOfCycle = true;
            } else if (nextCommand.equals("list")) {
                list(tasks, count);
            } else if (commandToWords[0].equals("done")) {
                int itemNum = Integer.parseInt(commandToWords[1]);
                done(tasks, itemNum);
            } else if (commandToWords[0].equals("todo")) {
                count++;
                todo(nextCommand.substring(5), tasks, count);
            } else if (commandToWords[0].equals("deadline")) {
                count++;
                deadline(nextCommand.substring(9, nextCommand.indexOf("/") - 1), dateInfo, tasks, count);
            } else if (commandToWords[0].equals("event")){
                count++;
                event(nextCommand.substring(6, nextCommand.indexOf("/") - 1), dateInfo, tasks, count);
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
     * @param command The description of the task.
     * @param tasks The Task array containing user tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void todo(String command, Task[] tasks, int count) {
        tasks[count - 1] = new Todo(command);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks[count - 1].toString());
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds a Deadline task.
     * @param command The description of the task.
     * @param by The deadline for the task to be completed.
     * @param tasks The Task array containing user tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void deadline(String command, String by, Task[] tasks, int count) {
        tasks[count - 1] = new Deadline(command, by);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task: \n" + "    " + tasks[count - 1].toString());
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    /**
     * Adds an Event task.
     * @param command The description of the task.
     * @param at The timing for the event.
     * @param tasks The Task array containing user tasks in sequence, up to 100.
     * @param count The current number of tasks stored inside the Task array.
     */
    public static void event(String command, String at, Task[] tasks, int count) {
        tasks[count - 1] = new Event(command, at);
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
     * @param itemNum The item number to be marked as done.
     */
    public static void done(Task[] tasks, int itemNum) {
        tasks[itemNum - 1].makeDone();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:\n" + tasks[itemNum - 1].toString());
        System.out.println("----------------------------------------------------------------------------------------");
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

