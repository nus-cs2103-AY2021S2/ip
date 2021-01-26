import java.util.Scanner;
import java.util.List;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String REPLY_OUTLINE = INDENTATION + "____________________________________________________________";

    private static List<Task> tasks = new ArrayList<>();

    // Helper functions
    public static String formatLine(String line) {
        return INDENTATION + line + "\n";
    }

    public static void reply(String msg) {
        System.out.println(REPLY_OUTLINE + "\n" + msg + REPLY_OUTLINE + "\n");
    }

    public static void addedTaskReply(Task task) {
        String msg = formatLine("Got it. I've added this task:")
                + formatLine("  " + task)
                + formatLine("Now you have " + tasks.size() + " tasks in the list.");
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
        String msg = formatLine("Hello! I'm Duke")
                + formatLine("What can I do for you?");
        reply(msg);
    }

    public static void addTodo(Command command) {
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        addedTaskReply(todo);
    }

    public static void addDeadline(Command command) throws DateTimeParseException {
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
            String msg = formatLine("Nice! I've marked this task as done:")
                    + formatLine("  " + tasks.get(index));
            reply(msg);
        }
    }

    public static void delete(Command command) {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            Task deletedTask = tasks.remove(index);
            String msg = formatLine("Noted. I've removed this task:")
                    + formatLine("  " + deletedTask)
                    + formatLine("Now you have " + tasks.size() + " tasks in the list.");
            reply(msg);
        }
    }

    public static void list() {
        String msg = formatLine("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            msg += formatLine((i + 1) + ". " + tasks.get(i));            
        }
        reply(msg);
    }

    public static void exit() {
        reply(formatLine("Bye. Hope to see you again soon!"));
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
            reply(formatLine(exception.getMessage()));
        } catch (DateTimeParseException exception) {
            System.out.println(exception.getMessage());
            reply(formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
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
