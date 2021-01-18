import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String REPLY_OUTLINE = INDENTATION + "____________________________________________________________";

    private static List<Task> tasks = new ArrayList<>();

    // Helper functions
    public static void reply(String msg) {
        System.out.println(REPLY_OUTLINE + "\n" + msg + REPLY_OUTLINE + "\n");
    }

    public static void addedTaskReply(Task task) {
        String msg = INDENTATION + "Got it. I've added this task:\n"
                + INDENTATION + "  " + task + "\n"
                + INDENTATION + "Now you have " + tasks.size() + " tasks in the list.\n";
        reply(msg);
    }

    public static Command parseInput(String input) {
        String[] parts = input.split(" ", 2);

        Command command;
        if (parts.length < 2) {
            command = new Command(parts[0]);
        } else {
            command = new Command(parts[0], parts[1]);
        }

        return command;
    }

    public static int getIndex(String args) {
        return Integer.parseInt(args) - 1;
    }

    // Commands
    public static void greet() {
        String msg = INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?\n";
        reply(msg);
    }

    public static void addTodo(Command command) {
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        addedTaskReply(todo);
    }

    public static void addDeadline(Command command) {
        String[] parts = command.getArgs().split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task deadline = new Deadline(parts[0], parts[1]);
            tasks.add(deadline);
            addedTaskReply(deadline);
        }
    }

    public static void addEvent(Command command) {
        String[] parts = command.getArgs().split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task event = new Event(parts[0], parts[1]);
            tasks.add(event);
            addedTaskReply(event);
        }
    }

    public static void done(Command command) {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            tasks.get(index).markDone();
            String msg = INDENTATION + "Nice! I've marked this task as done:\n"
                    + INDENTATION + "  " + tasks.get(index) + "\n";
            reply(msg);
        }
    }

    public static void delete(Command command) {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            Task deletedTask = tasks.remove(index);
            String msg = INDENTATION + "Noted. I've removed this task:\n"
                    + INDENTATION + "  " + deletedTask + "\n"
                    + INDENTATION + "Now you have " + tasks.size() + " tasks in the list.\n";
            reply(msg);
        }
    }

    public static void list() {
        String msg = INDENTATION + "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            msg += INDENTATION + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        reply(msg);
    }

    public static void exit() {
        reply(INDENTATION + "Bye. Hope to see you again soon!\n");
    }

    public static boolean readInput(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            Command command = parseInput(input);

            switch (command.getInstruction()) {
                case Command.BYE:
                    exit();
                    return false;
                case Command.LIST:
                    list();
                    break;
                case Command.DONE:
                    done(command);
                    break;
                case Command.DELETE:
                    delete(command);
                    break;
                case Command.TODO:
                    addTodo(command);
                    break;
                case Command.EVENT:
                    addEvent(command);
                    break;
                case Command.DEADLINE:
                    addDeadline(command);
                    break;
                default:
                    throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | EmptyDescriptionException | InvalidTaskException | TaskNotFoundException exception) {
            reply(INDENTATION + exception.getMessage() + "\n");
        }

        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner sc = new Scanner(System.in);

        while (readInput(sc));
    }
}
