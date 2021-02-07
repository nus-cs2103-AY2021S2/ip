import java.io.IOException;
import java.time.LocalDate;

/**
 * Parser handles the user commands.
 */
public class Parser {

    public static final int INDEX_OFFSET = 1;
    public static final int OFFSET_TO_NEXT_REQUIRED_DATA = 4;

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
     * Handles the ToDo command.
     * @return A string with the response to the command.
     * @throws IOException On file error when adding the ToDo task
     * to the file in the hard disk.
     */
    public String handleToDo() throws IOException {
        String descriptionTask = command.substring(index + INDEX_OFFSET);
        ToDo newToDo = new ToDo(descriptionTask);

        tasks.add(newToDo);
        storage.addTask(newToDo);
        return ui.respondToAddTask(newToDo, tasks.getSize());
    }

    /**
     * Handles the Deadline command.
     * @return A string with the response to the command.
     */
    public String handleDeadline() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionDeadline = command.substring(index + INDEX_OFFSET, findSlash - INDEX_OFFSET);
            LocalDate date = DateValidation.handleDate(command.substring(findSlash + OFFSET_TO_NEXT_REQUIRED_DATA));
            Deadline newDeadline = new Deadline(descriptionDeadline, date);

            tasks.add(newDeadline);
            storage.addTask(newDeadline);
            return ui.respondToAddTask(newDeadline, tasks.getSize());

        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the Event command.
     * @return A string with the response to the command.
     */
    public String handleEvent() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionEvent = command.substring(index + INDEX_OFFSET, findSlash - INDEX_OFFSET);
            String time = command.substring(findSlash + OFFSET_TO_NEXT_REQUIRED_DATA);
            Event newEvent = new Event(descriptionEvent, time);

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
            taskIdentifier = Integer.parseInt(command.substring(index + INDEX_OFFSET));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task toMark = tasks.find(taskIdentifier - INDEX_OFFSET);

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
            taskIdentifier = Integer.parseInt(command.substring(index + INDEX_OFFSET));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task selected = tasks.find(taskIdentifier - INDEX_OFFSET);

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
        System.out.println("HERE !!!!!");
        String output = "";
        this.command = command;
        try {
            Validation.checkValidCommand(command);
            index = command.indexOf(' ');
            findSlash = command.indexOf('/');

            if (index > -1) {
                String type = command.substring(0, index);
                switch (type) {
                case "todo":
                    output = handleToDo();
                    break;
                case "deadline":
                    output = handleDeadline();
                    break;
                case "event":
                    output = handleEvent();
                    break;
                case "done":
                    output = handleDone();
                    break;
                case "delete":
                    output = handleDelete();
                    break;
                case "find":
                    output = handleFind();
                    break;
                default:
                    break;
                }
            } else {
                switch (command) {
                case "bye":
                    output = ui.respondToBye();
                    break;
                case "list":
                    output = ui.respondToList(tasks.getSize()) + "\n" + tasks.list();
                    break;
                default:
                    break;
                }
            }
            System.out.println(output);
            return output;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

}
