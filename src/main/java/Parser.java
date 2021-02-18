import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Parser handles the user commands.
 */
public class Parser {

    public static final int INDEX_OFFSET = 1;
    public static final int OFFSET_TO_NEXT_REQUIRED_DATA = 4;
    public static final int INVALID_INDEX = -1;

    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected String command;

    protected int index;
    protected int findSlash;
    protected int taskIdentifier;

    /**
     * Creates a new instance of a Parser to make sense of user command.
     *
     * @param storage Storage of tasks on hard disk.
     * @param tasks List of user's tasks.
     * @param ui Ui for user interaction.
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Helper function to obtain necessary details and create a ToDo.
     * @return New ToDo created.
     */
    public ToDo createTodo() {
        String descriptionTask = command.substring(index + INDEX_OFFSET);
        ToDo newToDo = new ToDo(descriptionTask);
        return newToDo;
    }

    /**
     * Helper function to obtain necessary details and create a Deadline/Event.
     *
     * @param taskType A Character to indicate if command is a Deadline or Event.
     * @return A Task object, either a Deadline or Event object.
     * @throws DukeException On validation error.
     */
    public Task createDeadlineOrEvent(Character taskType) throws DukeException {
        CommandValidation.checkForContentAfterSlash(command, findSlash);
        String description = command.substring(index + INDEX_OFFSET, findSlash - INDEX_OFFSET);
        LocalDateTime taskDateTime =
                DateTimeHandler.validateDateTime(command.substring(findSlash + OFFSET_TO_NEXT_REQUIRED_DATA));

        if (taskType == 'D') {
            Deadline newDeadline = new Deadline(description, taskDateTime);
            return newDeadline;
        } else if (taskType == 'E') {
            Event newEvent = new Event(description, taskDateTime);
            return newEvent;
        } else {
            return null;
        }
    }

    /**
     * Helper function to find a task needed for handling commands.
     * @return Task needed.
     * @throws DukeException On validation error.
     */
    public Task findTask() throws DukeException {
        if (command.substring(0, index).equals("tag")) {
            taskIdentifier = Integer.parseInt(command.substring(index + INDEX_OFFSET, findSlash - INDEX_OFFSET));
        } else {
            taskIdentifier = Integer.parseInt(command.substring(index + INDEX_OFFSET));
        }
        CommandValidation.checkValidRange(tasks.getSize(), taskIdentifier);
        Task task = tasks.find(taskIdentifier - INDEX_OFFSET);
        return task;
    }

    /**
     * Handles a ToDo, Deadline or Event command.
     * @param type A Character to indicate type of command.
     *
     * @return A string with the response to the command.
     * @throws IOException On file error when adding the task
     * to the file in the hard disk.
     * @throws DukeException On validation error.
     */
    public String handleATaskCreation(Character type) throws IOException, DukeException {
        assert type == 'T' | type == 'D' | type == 'E';
        Task newTask = null;
        if (type == 'T') {
            newTask = createTodo();
        } else {
            newTask = createDeadlineOrEvent(type);
        }
        tasks.add(newTask);
        storage.addTask(newTask);
        return ui.respondToAddTask(newTask, tasks.getSize());
    }

    /**
     * Handles the Done command.
     * @return A string with the response to the command.
     * @throws DukeException On validation error.
     * @throws IOException On file error.
     */
    public String handleDone() throws DukeException, IOException {
        Task toMark = findTask();
        storage.markTask(toMark);
        return ui.respondToDone(toMark);
    }

    /**
     * Handles the Delete command.
     * @return A string with the response to the command.
     * @throws DukeException On validation error.
     * @throws IOException On file error.
     */
    public String handleDelete() throws DukeException, IOException {
        Task selected = findTask();
        tasks.delete(taskIdentifier - INDEX_OFFSET);
        storage.deleteTask(selected);
        return ui.respondToDelete(selected, tasks.getSize());
    }

    /**
     * Handles the Find command.
     * @return A string with the response to the command.
     */
    public String handleFind() {
        String keyword = command.substring(index + INDEX_OFFSET);
        String output = "";
        int numberOfMatches = 0;
        int tracker = 0;

        for (int i = 0; i < tasks.getSize(); i++) {
            String task = tasks.find(i).toString();
            boolean isPresent = task.toLowerCase().contains(keyword.toLowerCase());
            if (isPresent) {
                if (tracker == 0) {
                    output = ui.respondToFind() + "\n";
                    tracker++;
                }
                output = output + task + "\n";
                numberOfMatches++;
            }
        }
        if (numberOfMatches == 0) {
            output = ui.respondToNoMatches();
        }
        return output;
    }

    /**
     * Extracts the tag from the command.
     * @return A string representing the tag.
     */
    public String extractTag() {
        return command.substring(findSlash + INDEX_OFFSET);
    }

    /**
     * Handles the Tag command.
     * @return A string with the response to the command.
     * @throws DukeException On validation error.
     * @throws IOException On file error.
     */
    public String handleTag() throws DukeException, IOException {
        CommandValidation.checkForContentAfterSlash(command, findSlash);
        String tag = extractTag();
        Task selected = findTask();
        storage.addTag(selected, tag);
        selected.setTag(tag);
        return ui.respondToTag(selected);
    }

    /**
     * Main method to make sense of the user command and to determine
     * which command it is.
     *
     * @param command User's command input.
     * @return A string with the response to the command.
     */
    public String handleCommand(String command) {
        this.command = command;
        try {
            CommandValidation.checkValidCommand(command);
            index = command.indexOf(' ');
            findSlash = command.indexOf('/');

            if (index > INVALID_INDEX) {
                String type = command.substring(0, index);
                switch (type) {
                case "todo":
                    return handleATaskCreation('T');
                case "deadline":
                    return handleATaskCreation('D');
                case "event":
                    return handleATaskCreation('E');
                case "done":
                    return handleDone();
                case "delete":
                    return handleDelete();
                case "find":
                    return handleFind();
                case "tag":
                    return handleTag();
                default:
                    return null;
                }
            } else {
                switch (command) {
                case "bye":
                    return ui.respondToBye();
                case "list":
                    return ui.respondToList(tasks.getSize()) + "\n" + tasks.list();
                case "help":
                    return ui.respondToHelp();
                default:
                    return null;
                }
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

}
