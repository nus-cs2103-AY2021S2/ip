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

    private Ui ui;
    private File file;
    private List<Task> tasks;

    // Helper functions
    public Command parseInput(String input) {
        String[] parts = input.split(" ", 2);

        Command command;
        if (parts.length < 2) {
            command = new Command(parts[0]);
        } else {
            command = new Command(parts[0], parts[1]);
        }

        return command;
    }

    public int getIndex(String args) {
        return Integer.parseInt(args) - 1;
    }

    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(task.toFileString());
        fw.close();
    }

    // Commands
    public void addTodo(Command command) throws IOException {
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        writeToFile(todo);
        ui.addedTaskReply(todo, tasks.size());
    }

    public void addTodo(String[] parts) {
        Task todo = new Todo(parts[2]);
        if (parts[1].equals("X")) {
            todo.markDone();
        }
        tasks.add(todo);
    }

    public void addDeadline(Command command) throws IOException, DateTimeParseException {
        String[] parts = command.getArgs().split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task deadline = new Deadline(parts[0], parts[1]);
            tasks.add(deadline);
            writeToFile(deadline);
            ui.addedTaskReply(deadline, tasks.size());
        }
    }

    public void addDeadline(String[] parts) throws DateTimeParseException {
        Task deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("X")) {
            deadline.markDone();
        }
        tasks.add(deadline);
    }

    public void addEvent(Command command) throws IOException {
        String[] parts = command.getArgs().split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task event = new Event(parts[0], parts[1]);
            tasks.add(event);
            writeToFile(event);
            ui.addedTaskReply(event, tasks.size());
        }
    }

    public void addEvent(String[] parts) {
        Task event = new Event(parts[2], parts[3]);
        if (parts[1].equals("X")) {
            event.markDone();
        }
        tasks.add(event);
    }

    public void updateFile() throws IOException {
        StringBuffer buffer = new StringBuffer();
        for (Task task : tasks) {
            buffer.append(task.toFileString());
        }

        FileWriter fw = new FileWriter(file);
        fw.write(buffer.toString());
        fw.close();
    }

    public void done(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            tasks.get(index).markDone();
            updateFile();
            ui.markDoneReply(tasks.get(index));
        }
    }

    public void delete(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            Task deletedTask = tasks.remove(index);
            updateFile();
            ui.deleteReply(deletedTask, tasks.size());
        }
    }

    public boolean readInput(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            Command command = parseInput(input);

            switch (command.getInstruction()) {
            case Command.BYE:
                ui.exit();
                return false;
            case Command.LIST:
                ui.list(tasks);
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
            ui.reply(ui.formatLine(exception.getMessage()));
        } catch (DateTimeParseException exception) {
            ui.reply(ui.formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
        }

        return true;
    }

    public void readFromFile(String input) {
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
        } catch (UnknownCommandException exception) {
            ui.reply(ui.formatLine(exception.getMessage()));
        } catch (DateTimeParseException exception) {
            ui.reply(ui.formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format"));
        }
    }

    public Duke() throws IOException {
        tasks = new ArrayList<>();
        ui = new Ui();

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

        ui.greet();
        Scanner sc = new Scanner(System.in);

        while (readInput(sc));
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }
}
