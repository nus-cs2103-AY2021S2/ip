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
     * @throws IOException On file error when adding the ToDo task
     * to the file in the hard disk.
     */
    public String handleToDo() throws IOException {
        String descriptionTask = command.substring(index + 1);
        ToDo newToDo = new ToDo(descriptionTask);

        tasks.add(newToDo);
        storage.addTask(newToDo);
        return ui.responseToAddTask(newToDo, tasks.getSize());
    }

    /**
     * Handles the Deadline command.
     */
    public String handleDeadline() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionDeadline = command.substring(index + 1, findSlash - 1);
            LocalDate date = DateValidation.handleDate(command.substring(findSlash + 4));
            Deadline newDeadline = new Deadline(descriptionDeadline, date);

            tasks.add(newDeadline);
            storage.addTask(newDeadline);
            return ui.responseToAddTask(newDeadline, tasks.getSize());

        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }

    /**
     * Handles the Event command.
     */
    public String handleEvent() {
        try {
            Validation.checkForSchedule(command, findSlash);
            String descriptionEvent = command.substring(index + 1, findSlash - 1);
            String time = command.substring(findSlash + 4);
            Event newEvent = new Event(descriptionEvent, time);

            tasks.add(newEvent);
            storage.addTask(newEvent);
            return ui.responseToAddTask(newEvent, tasks.getSize());

        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }

    /**
     * Handles the Done command.
     */
    public String handleDone() {
        try {
            taskIdentifier = Integer.parseInt(command.substring(index + 1));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task toMark = tasks.find(taskIdentifier - 1);

            storage.markTask(toMark);
            return ui.responseToDone(toMark);

        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }

    /**
     * Handles the Delete command.
     */
    public String handleDelete() {
        try {
            taskIdentifier = Integer.parseInt(command.substring(index + 1));
            Validation.checkValidRange(tasks.getSize(), taskIdentifier);
            Task selected = tasks.find(taskIdentifier - 1);

            tasks.delete(taskIdentifier - 1);
            storage.deleteTask(selected);
            return ui.responseToDelete(selected, tasks.getSize());

        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }

    /**
     * Handles the Find command.
     */
    public String handleFind() {
        String keyword = command.substring(index + 1);
        return tasks.findWithKeyword(keyword);
    }

    /**
     * Main method to make sense of the user command and to determine
     * which command it is.
     *
     * @param command User's command input.
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
                    output = ui.responseToBye();
                    break;
                case "list":
                    output = ui.responseToList(tasks.getSize()) + "\n" + tasks.list();
                    break;
                default:
                    break;
                }
            }
            System.out.println(output);
            return output;
        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }



}
