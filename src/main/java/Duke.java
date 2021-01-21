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
     * @param command a string which needs to be parsed
     */
    public static void addTask(String command) {
        String type = command.split(" ")[0];
        String substr = command.replaceFirst(type + " ", "");
        Task newTask;
        String title = "";
        switch (type) {
            case "todo":
                title = substr;
                newTask = new ToDo(title);
                tasks[counter] = newTask;
                break;
            case "deadline":
                int idxOfBy = substr.indexOf("/by");
                if (idxOfBy == -1) {
                    ifError();
                    return;
                } else {
                    title = substr.substring(0, idxOfBy - 1);
                    String deadline = substr.substring(idxOfBy + 4);
                    newTask = new Deadline(title, deadline);
                    tasks[counter] = newTask;
                }
                break;
            case "event":
                int idxOfAt = substr.indexOf("/at");
                if (idxOfAt == -1) {
                    ifError();
                    return;
                } else {
                    title = substr.substring(0, idxOfAt - 1);
                    String time = substr.substring(idxOfAt + 4);
                    newTask = new Event(title, time);
                    tasks[counter] = newTask;
                }
                break;
            default:
                ifError();
                return;
        }
        counter++;
        printMsg("Got it. I've added this task: ");
        printMsg("  " + newTask);
        printMsg("Now you have " + counter + " tasks in the list.");
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
            switch (substrs[0]) {
                case "todo", "event", "deadline":
                    addTask(command);
                    break;
                case "done":
                    try {
                        int idx = Integer.parseInt(substrs[1]);
                        doneTask(tasks[idx]);
                    } catch (Exception e) {
                        ifError();
                    }
                    break;
                default:
                    ifError();
            }
        }
        printLine();
        return true;
    }

    public static void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }

    /**
     * print error message if the input cannot be parsed
     */
    public static void ifError() {
        printMsg("unknown command or incorrect input format");
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
