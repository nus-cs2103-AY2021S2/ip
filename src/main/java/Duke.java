import java.util.Scanner;
import java.util.ArrayList;

/**
 * the agent program to run Duke
 */
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greeting();
        while(true) {
            String command = sc.nextLine();
            printLine();
            boolean canContinue = true;
            try {
                canContinue = processCommand(command);
            } catch (DukeException e) {
                printMsg(e.getMessage());
            }
            printLine();
            if (!canContinue) {
                break;
            }
        }
        sc.close();
    }

    /**
     * print message with proper indentations
     * @param msg the message without indentation
     */
    public static void printMsg(String msg) {
        System.out.println("     " + msg);
    }

    /**
     * @param command the input of user
     * @throws DukeException exception specific to Duke
     */
    public static void deleteTask(String command) throws DukeException {
        try {
            int idx = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(idx);
            task.done();
            printMsg("Nice! I've marked this task as done: ");
            printMsg("  " + task);
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * @param command the input of user
     * @throws DukeException exception specific to Duke
     */
    public static void addTask(String command) throws DukeException {
        String type = command.split(" ")[0];
        if (command.split(" ").length < 2 && type.equals("todo")) {
            throw new EmptyTodoDescriptionException();
        }
        String substr = command.replaceFirst(type + " ", "");
        Task newTask;
        String title;
        switch (type) {
            case "todo":
                title = substr;
                newTask = new ToDo(title);
                tasks.add(newTask);
                break;
            case "deadline":
                int idxOfBy = substr.indexOf("/by");
                if (idxOfBy == -1) {
                    throw new DukeException();
                } else {
                    title = substr.substring(0, idxOfBy - 1);
                    String deadline = substr.substring(idxOfBy + 4);
                    newTask = new Deadline(title, deadline);
                    tasks.add(newTask);
                }
                break;
            case "event":
                int idxOfAt = substr.indexOf("/at");
                if (idxOfAt == -1) {
                    throw new DukeException();
                } else {
                    title = substr.substring(0, idxOfAt - 1);
                    String time = substr.substring(idxOfAt + 4);
                    newTask = new Event(title, time);
                    tasks.add(newTask);
                }
                break;
            default:
                throw new DukeException();
        }
        printMsg("Got it. I've added this task: ");
        printMsg("  " + newTask);
        printMsg("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * @param command the input of user
     * @throws DukeException exception specific to Duke
     */
    public static void doneTask(String command) throws DukeException {
        try {
            int idx = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(idx);
            task.done();
            printMsg("Nice! I've marked this task as done: ");
            printMsg("  " + task);
        } catch (Exception e) {
            throw new DukeException();
        }
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
     * @param command the input of user
     * @return false if command entered is "bye" else true
     */
    public static boolean processCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            sayBye();
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
                    doneTask(command);
                    break;
                case "delete":
                    deleteTask(command);
                    break;
                default:
                    throw new UnknownCommandException();
            }
        }
        return true;
    }

    /**
     * prints the farewell message
     */
    public static void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }

    /**
     * print task list as well as their status
     */
    public static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            printMsg((i + 1) + "." + tasks.get(i).toString());
        }
    }
}
