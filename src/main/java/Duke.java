import java.util.Scanner;

/**
 * The Duke project
 */
public class Duke {
    public static void main(String[] args) throws InterruptedException {
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
        System.out.println("Hello! I'm Jay \n" + "What is your name!");
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
     * Add what the user typed as input by printing out what the user keyed in.
     * @param command The user input.
     */
    public static void add(String command, Task[] tasks, int count) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("added: " + command);
        System.out.println("----------------------------------------------------------------------------------------");
        tasks[count] = new Task(command);
    }

    /**
     * List out all user inputs in sequence.
     * @param tasks A list of user inputs in sequence, up to 100.
     * @param count The current number of user inputs stored inside the list.
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

    public static void done(Task[] tasks, int itemNum) throws InterruptedException {
        tasks[itemNum - 1].isDone = true;
        Thread.sleep(500);
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

