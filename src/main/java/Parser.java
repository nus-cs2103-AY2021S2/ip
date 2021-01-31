import java.io.IOException;
import java.time.LocalDate;

/**
 * Parser handles the user commands.
 */
public class Parser {

    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected String command;
    protected int index;
    protected int findSlash;
    protected int taskIdentifier;

    /**
     * Creates a new instance of a Parser to make sense of user command.
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
     * @throws IOException On file error when adding the ToDo task
     * to the file in the hard disk.
     */
    public void handleToDo() throws IOException {
        String descriptionTask = command.substring(index + 1);
        ToDo newToDo = new ToDo(descriptionTask);

        tasks.add(newToDo);
        storage.addTask(newToDo);
        ui.responseToAddTask(newToDo, tasks.getSize());
    }

    /**
     * Handles the Deadline command.
     */
    public void handleDeadline() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionDeadline = command.substring(index + 1, findSlash - 1);
            LocalDate date = DateValidation.handleDate(command.substring(findSlash + 4));
            Deadline newDeadline = new Deadline(descriptionDeadline, date);

            tasks.add(newDeadline);
            storage.addTask(newDeadline);
            ui.responseToAddTask(newDeadline, tasks.getSize());

        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the Event command.
     */
    public void handleEvent() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionEvent = command.substring(index + 1, findSlash - 1);
            String time = command.substring(findSlash + 4);
            Event newEvent = new Event(descriptionEvent, time);

            tasks.add(newEvent);
            storage.addTask(newEvent);
            ui.responseToAddTask(newEvent, tasks.getSize());

        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the Done command.
     */
    public void handleDone() {
        try {
            taskIdentifier = Integer.parseInt(command.substring(index + 1));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task toMark = tasks.find(taskIdentifier - 1);

            storage.markTask(toMark);
            ui.responseToDone(toMark);

        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the Delete command.
     */
    public void handleDelete() {
        try {
            taskIdentifier = Integer.parseInt(command.substring(index + 1));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task selected = tasks.find(taskIdentifier - 1);

            tasks.delete(taskIdentifier - 1);
            storage.deleteTask(selected);
            ui.responseToDelete(selected, tasks.getSize());

        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the Find command.
     */
    public void handleFind() {
        String keyword = command.substring(index + 1);
        tasks.findWithKeyword(keyword);
    }

    /**
     * Main method to make sense of the user command and to determine
     * which command it is.
     * @param command User's command input.
     */
    public void handleCommand(String command) {
        this.command = command;
        try {
            Validation.checkValidCommand(command);
            index = command.indexOf(' ');
            findSlash = command.indexOf('/');

            if (index > -1) {
                String type = command.substring(0, index);
                switch (type) {
                case "todo":
                    handleToDo();
                    break;
                case "deadline":
                    handleDeadline();
                    break;
                case "event":
                    handleEvent();
                    break;
                case "done":
                    handleDone();
                    break;
                case "delete":
                    handleDelete();
                    break;
                case "find":
                    handleFind();
                    break;
                default:
                    break;
                }
            } else {
                switch (command) {
                case "bye":
                    ui.responseToBye();
                    break;
                case "list":
                    ui.responseToList(tasks.getSize());
                    tasks.list();
                    break;
                default:
                    break;
                }
            }
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }



}
