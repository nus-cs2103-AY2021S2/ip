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
            if (nextCommand.equals("bye")) {
                bye(username);
                endOfCycle = true;
            } else if (nextCommand.equals("list")) {
                list(tasks, count);
            } else if (commandToWords[0].equals("done")) {
                int itemNum = Integer.parseInt(commandToWords[1]);
                done(tasks, itemNum);
            }
            else {
                add(nextCommand, tasks, count);
                count++;
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
     * Add what the user typed as input by adding it into the Task array.
     * Show the user the task that has been added in the display.
     * @param command The user input.
     * @param tasks The Task array containing all the user tasks.
     * @param count The number of tasks in the array at the moment.
     */
    public static void add(String command, Task[] tasks, int count) {
        tasks[count] = new Task(command);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("added: " + command);
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
            System.out.println(listNum + ". " + tasks[i].display());
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
        System.out.println("Nice! I've marked this task as done:\n" + tasks[itemNum - 1].display());
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

