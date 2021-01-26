import java.util.Scanner;
import java.util.List;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final String INDENTATION = "    ";
    private static final String REPLY_OUTLINE = INDENTATION + "____________________________________________________________";

    private static List<Task> tasks = new ArrayList<>();
    private static File file;

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

    public static void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(task.toFileString());
        fw.close();
    }

    // Commands
    public static void greet() {
        String msg = formatLine("Hello! I'm Duke")
                + formatLine("What can I do for you?");
        reply(msg);
    }

    public static void addTodo(Command command) throws IOException {
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        writeToFile(todo);
        addedTaskReply(todo);
    }

    public static void addTodo(String[] parts) {
        Task todo = new Todo(parts[2]);
        if (parts[1].equals("X")) {
            todo.markDone();
        }
        tasks.add(todo);
    }

    public static void addDeadline(Command command) throws IOException, DateTimeParseException {
        String[] parts = command.getArgs().split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task deadline = new Deadline(parts[0], parts[1]);
            tasks.add(deadline);
            writeToFile(deadline);
            addedTaskReply(deadline);
        }
    }

    public static void addDeadline(String[] parts) throws DateTimeParseException {
        Task deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("X")) {
            deadline.markDone();
        }
        tasks.add(deadline);
    }

    public static void addEvent(Command command) throws IOException {
        String[] parts = command.getArgs().split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task event = new Event(parts[0], parts[1]);
            tasks.add(event);
            writeToFile(event);
            addedTaskReply(event);
        }
    }

    public static void addEvent(String[] parts) {
        Task event = new Event(parts[2], parts[3]);
        if (parts[1].equals("X")) {
            event.markDone();
        }
        tasks.add(event);
    }

    public static void updateFile() throws IOException {
        StringBuffer buffer = new StringBuffer();
        for (Task task : tasks) {
            buffer.append(task.toFileString());
        }

        FileWriter fw = new FileWriter(file);
        fw.write(buffer.toString());
        fw.close();
    }

    public static void done(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            tasks.get(index).markDone();
            updateFile();
            String msg = formatLine("Nice! I've marked this task as done:")
                    + formatLine("  " + tasks.get(index));
            reply(msg);
        }
    }

    public static void delete(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            Task deletedTask = tasks.remove(index);
            updateFile();
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
        } catch (UnknownCommandException | EmptyDescriptionException | InvalidTaskException | TaskNotFoundException | IOException exception) {
            reply(formatLine(exception.getMessage()));
        } catch (DateTimeParseException exception) {
            System.out.println(exception.getMessage());
            reply(formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
        }

        return true;
    }

    public static void readFromFile(String input) {
        String[] parts = Arrays.stream(input.split("\\|"))
            .map(String::trim)
            .toArray(String[]::new);

        try {
            switch (parts[0]) {
                case Command.TODO:
                    addTodo(parts);
                    break;
                case Command.EVENT:
                    addEvent(parts);
                    break;
                case Command.DEADLINE:
                    addDeadline(parts);
                    break;
                default:
                    throw new UnknownCommandException();
                }
        } catch (DateTimeParseException exception) {
            System.out.println(exception.getMessage());
            reply(formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        File dir = new File(DIR_NAME);
        if (!dir.exists()) {
            dir.mkdir();
        }

        file = new File(DIR_NAME + "/" + FILE_NAME);
        if (!file.createNewFile()) {
            Scanner fileSc = new Scanner(file);
            while (fileSc.hasNextLine()) {
                readFromFile(fileSc.nextLine());
            }
            fileSc.close();
        }

        greet();
        Scanner sc = new Scanner(System.in);

        while (readInput(sc));
    }
}
