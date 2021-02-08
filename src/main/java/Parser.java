import java.io.IOException;
import java.time.LocalDate;

/**
 * Parser handles the user commands.
 */
public class Parser {

    public static final int INDEX_OFFSET = 1;
    public static final int OFFSET_TO_NEXT_REQUIRED_DATA = 4;
    public static final int VALID_INDEX_BOUND = -1;

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
     * Helper function to obtain necessary details and create a Deadline.
     * @return New Deadline created.
     */
    public Deadline createDeadline() throws DukeException {
        Validation.checkForSchedule(command, findSlash);
        String descriptionDeadline = command.substring(index + INDEX_OFFSET, findSlash - INDEX_OFFSET);
        LocalDate date = DateValidation.handleDate(command.substring(findSlash + OFFSET_TO_NEXT_REQUIRED_DATA));
        Deadline newDeadline = new Deadline(descriptionDeadline, date);
        return newDeadline;
    }

    /**
     * Helper function to obtain necessary details and create an Event.
     * @return New Event created.
     */
    public Event createEvent() throws DukeException {
        Validation.checkForSchedule(command, findSlash);
        String descriptionEvent = command.substring(index + INDEX_OFFSET, findSlash - INDEX_OFFSET);
        String time = command.substring(findSlash + OFFSET_TO_NEXT_REQUIRED_DATA);
        Event newEvent = new Event(descriptionEvent, time);
        return newEvent;
    }

    /**
     * Helper function to find a task needed for handling commands.
     * @return Task needed.
     */
    public Task findTask() throws DukeException {
        taskIdentifier = Integer.parseInt(command.substring(index + INDEX_OFFSET));
        Validation.checkValidRange(tasks.getSize(), taskIdentifier);
        Task task = tasks.find(taskIdentifier - INDEX_OFFSET);
        return task;
    }

    /**
     * Handles the ToDo command.
     * @return A string with the response to the command.
     * @throws IOException On file error when adding the ToDo task
     * to the file in the hard disk.
     */
    public String handleToDo() {
        try {
            ToDo newToDo = createTodo();
            tasks.add(newToDo);
            storage.addTask(newToDo);
            return ui.respondToAddTask(newToDo, tasks.getSize());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the Deadline command.
     * @return A string with the response to the command.
     */
    public String handleDeadline() {
        try {
            Deadline newDeadline = createDeadline();
            tasks.add(newDeadline);
            storage.addTask(newDeadline);
            return ui.respondToAddTask(newDeadline, tasks.getSize());
        } catch (IOException | DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the Event command.
     * @return A string with the response to the command.
     */
    public String handleEvent() {
        try {
            Event newEvent = createEvent();
            tasks.add(newEvent);
            storage.addTask(newEvent);
            return ui.respondToAddTask(newEvent, tasks.getSize());
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the Done command.
     * @return A string with the response to the command.
     */
    public String handleDone() {
        try {
            Task toMark = findTask();
            storage.markTask(toMark);
            return ui.respondToDone(toMark);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the Delete command.
     * @return A string with the response to the command.
     */
    public String handleDelete() {
        try {
            Task selected = findTask();
            tasks.delete(taskIdentifier - INDEX_OFFSET);
            storage.deleteTask(selected);
            return ui.respondToDelete(selected, tasks.getSize());
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the Find command.
     * @return A string with the response to the command.
     */
    public String handleFind() {
        String keyword = command.substring(index + INDEX_OFFSET);
        return tasks.findWithKeyword(keyword);
    }

    /**
     * Main method to make sense of the user command and to determine
     * which command it is.
     * @param command User's command input.
     * @return A string with the response to the command.
     */
    public String handleCommand(String command) {
        this.command = command;
        try {
            Validation.checkValidCommand(command);
            index = command.indexOf(' ');
            findSlash = command.indexOf('/');

            if (index > VALID_INDEX_BOUND) {
                String type = command.substring(0, index);
                switch (type) {
                case "todo":
                    return handleToDo();
                case "deadline":
                    return handleDeadline();
                case "event":
                    return handleEvent();
                case "done":
                    return handleDone();
                case "delete":
                    return handleDelete();
                case "find":
                    return handleFind();
                default:
                    return null;
                }
            } else {
                switch (command) {
                case "bye":
                    return ui.respondToBye();
                case "list":
                    return ui.respondToList(tasks.getSize()) + "\n" + tasks.list();
                default:
                    return null;
                }
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
