import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greeting();
        while(true) {
            String command = sc.nextLine();
            if (!processCommand(command)) {
                break;
            }
        }
        sc.close();
    }

    /**
     * print message with proper indentations
     * @param msg
     */
    public static void printMsg(String msg) {
        System.out.println("     " + msg);
    }

    /**
     * @param task a new task that is added to the task list
     */
    public static void addTask(Task task) {
        tasks[counter] = task;
        counter++;
        printMsg("added: " + task.getTitle());
    }

    /**
     * @param task the task that is marked done
     */
    public static void doneTask(Task task) {
        task.done();
        printMsg("Nice! I've marked this task as done: ");
        printMsg("  " + task);
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * print greeting message
     */
    public static void greeting() {
        printLine();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printMsg("Hello! I'm Duke");
        printMsg("What can I do for you?");
        printLine();
    }

    /**
     * map commands to actions
     * @param command
     * @return false if command entered is "bye" else true
     */
    public static boolean processCommand(String command) {
        printLine();
        if (command.equals("bye")) {
            sayBye();
            printLine();
            return false;
        }
        
        if (command.equals("list")) {
            printTasks();
        } else {
            String[] substrs = command.split(" ");
            if (substrs[0].equals("done")) {
                try {
                    int idx = Integer.parseInt(substrs[1]);
                    doneTask(tasks[idx]);
                } catch (Exception e) {
                    addTask(new Task(command));
                }
            } else {
                addTask(new Task(command));
            }
        }
        printLine();
        return true;
    }

    public static void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }

    /**
     * print task list as well as their status
     */
    public static void printTasks() {
        for (int i = 0; i < counter; i++) {
            printMsg(i + "." + tasks[i].toString());
        }
    }
}
