import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";

    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage(DIR_NAME, FILE_NAME);

        try {
            storage.readFromFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new Todo to the TaskList and returns the reply.
     *
     * @param command Command with details about the Todo to be created.
     * @return Reply to the Todo command.
     * @throws IOException If an error occurs while writing to the file.
     */
    public String addTodo(Command command) throws IOException {
        assert command != null : "Command should be defined";
        Task todo = new Todo(command.getArgs());
        tasks.add(todo);
        storage.writeToFile(todo);
        return Ui.addedTaskReply(todo, tasks.size());
    }

    /**
     * Adds a new Deadline to the TaskList and returns the reply.
     *
     * @param command Command with details about the Deadline to be created.
     * @return Reply to the Deadline command.
     * @throws IOException If an error occurs while writing to the file.
     * @throws DateTimeParseException If date provided is not of the "dd/mm/yyyy hhmm" or "dd/mm/yyyy" format.
     */
    public String addDeadline(Command command) throws IOException, DateTimeParseException {
        assert command != null : "Command should be defined";
        String[] parts = command.getArgs().split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        }

        Task deadline = new Deadline(parts[0], parts[1]);
        tasks.add(deadline);
        storage.writeToFile(deadline);
        return Ui.addedTaskReply(deadline, tasks.size());
    }

    /**
     * Adds a new Event to the TaskList and returns the reply.
     *
     * @param command Command with details about the Event to be created.
     * @return Reply to the Event command.
     * @throws IOException If an error occurs while writing to the file.
     */
    public String addEvent(Command command) throws IOException {
        assert command != null : "Command should be defined";
        String[] parts = command.getArgs().split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException(command.getInstruction());
        }

        Task event = new Event(parts[0], parts[1]);
        tasks.add(event);
        storage.writeToFile(event);
        return Ui.addedTaskReply(event, tasks.size());
    }

    /**
     * Marks a task as done and returns the reply.
     *
     * @param command Command containing the index of the task to mark as done.
     * @return Reply to having marked a task as done.
     * @throws IOException If an error occurs while writing to the file.
     */
    public String done(Command command) throws IOException {
        assert command != null : "Command should be defined";
        int index = command.getIndex();

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        }

        tasks.getTaskAt(index).markDone();
        storage.updateFile(tasks);
        return Ui.markDoneReply(tasks.getTaskAt(index));
    }

    /**
     * Deletes a task and returns the reply.
     *
     * @param command Command containing the index of the task to delete.
     * @return Reply to having deleted a task.
     * @throws IOException If an error occurs while writing to the file.
     */
    public String delete(Command command) throws IOException {
        assert command != null : "Command should be defined";
        int index = command.getIndex();

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        }

        Task deletedTask = tasks.remove(index);
        storage.updateFile(tasks);
        return Ui.deleteReply(deletedTask, tasks.size());
    }

    /**
     * Finds and returns a list of tasks matching the given keyword
     *
     * @param command Command containing the keyword to search for.
     * @return Reply to find command.
     */
    public String find(Command command) {
        assert command != null : "Command should be defined";
        String keyword = command.getArgs();

        TaskList matchingTasks = tasks.findMatchingTasks(keyword);
        return Ui.listMatchingTasks(matchingTasks);
    }

    /**
     * Adds a tag to a task and returns the reply.
     *
     * @param command Command containing the task to tag, and the tag to add.
     * @return Reply to tag command.
     * @throws IOException If an error occurs while writing to the file.
     */
    public String tagTask(Command command) throws IOException {
        assert command != null : "Command should be defined";
        int index = command.getIndex();

        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException();
        }

        Task taskToTag = tasks.getTaskAt(index);
        taskToTag.addTag(command.getArgs());
        storage.updateFile(tasks);
        return Ui.tagTaskReply(taskToTag, command.getArgs());
    }

    /**
     * Processes user's input and executes the respective command
     *
     * @param input User's input string
     * @return False if the program should terminate, else true.
     */
    public String parseInputAndReply(String input) {
        try {
            Command command = Parser.parseInput(input.trim());

            switch (command.getInstruction()) {
            case Command.BYE:
                return Ui.exit();
            case Command.LIST:
                return Ui.list(tasks);
            case Command.DONE:
                return done(command);
            case Command.DELETE:
                return delete(command);
            case Command.FIND:
                return find(command);
            case Command.TODO:
                return addTodo(command);
            case Command.EVENT:
                return addEvent(command);
            case Command.DEADLINE:
                return addDeadline(command);
            case Command.TAG:
                return tagTask(command);
            default:
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | EmptyDescriptionException | EmptyTagException | InvalidTaskException
                | TaskNotFoundException | IOException exception) {
            return Ui.formatLine(exception.getMessage());
        } catch (DateTimeParseException exception) {
            return Ui.formatLine("☹ Please provide dates in the \"dd/mm/yyyy hhmm\" or \"dd/mm/yyyy\" format");
        } catch (NumberFormatException exception) {
            return Ui.formatLine("☹ Please provide a valid index of the task that you would like to access.");
        }
    }

    /**
     * Returns true if the provided input matches the bye command.
     *
     * @param input String entered by user.
     * @return True if the provided input matches the bye command, else false.
     */
    public boolean shouldExit(String input) {
        Command command = Parser.parseInput(input.trim());
        return command.getInstruction().equals(Command.BYE);
    }
}
