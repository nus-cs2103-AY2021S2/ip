import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
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

    public static void addTask(String task) {
        tasks[counter] = task;
        counter++;
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
        } else {
            switch (command) {
                case "list":
                    printTasks();
                    break;
                default:
                    addTask(command);
                    echo(command);
                    break;
            }
            printLine();
            return true;
        }
    }

    public static void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }

    public static void echo(String msg) {
        printMsg("added: " + msg);
    }

    public static void printTasks() {
        for (int i = 0; i < counter; i++) {
            printMsg(i + ". " + tasks[i]);
        }
    }
}
