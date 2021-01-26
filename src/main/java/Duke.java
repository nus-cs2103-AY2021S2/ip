import java.util.Scanner;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    // Helper functions
    public int getIndex(String args) {
        return Integer.parseInt(args) - 1;
    }

    // Commands
    public void addTodo(Command command) throws IOException {
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        storage.writeToFile(todo);
        ui.addedTaskReply(todo, tasks.size());
    }

    public void addDeadline(Command command) throws IOException, DateTimeParseException {
        String[] parts = command.getArgs().split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task deadline = new Deadline(parts[0], parts[1]);
            tasks.add(deadline);
            storage.writeToFile(deadline);
            ui.addedTaskReply(deadline, tasks.size());
        }
    }

    public void addEvent(Command command) throws IOException {
        String[] parts = command.getArgs().split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        } else {
            Task event = new Event(parts[0], parts[1]);
            tasks.add(event);
            storage.writeToFile(event);
            ui.addedTaskReply(event, tasks.size());
        }
    }

    public void done(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            tasks.getTaskAt(index).markDone();
            storage.updateFile(tasks);
            ui.markDoneReply(tasks.getTaskAt(index));
        }
    }

    public void delete(Command command) throws IOException {
        int index = getIndex(command.getArgs());

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        } else {
            Task deletedTask = tasks.remove(index);
            storage.updateFile(tasks);
            ui.deleteReply(deletedTask, tasks.size());
        }
    }

    public boolean readInput(Scanner sc) {
        String input = sc.nextLine().trim();

        try {
            Command command = Parser.parseInput(input);

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

    public Duke() throws IOException {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(DIR_NAME, FILE_NAME);
    
        storage.readFromFile(tasks, ui);
        ui.greet();

        Scanner sc = new Scanner(System.in);
        while (readInput(sc));
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }
}
