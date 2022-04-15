package duke.task;

import static duke.task.CommandManager.CommandType.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.datetime.DateTimeConverter;
import duke.error.ErrorChecker;
import duke.file.FileSaver;
import duke.history.ArchivedTask;
import duke.history.RedoHistory;
import duke.history.TaskArchive;
import duke.history.UndoHistory;

/**
 * Command manager decides what type of action to take based on the user input.
 *
 * @author  Nicole Ang
 */
public class CommandManager {
    public enum CommandType { BYE, HELP, LIST, DONE, DELETE, FIND, UNDO,
        REDO, TODO, DEADLINE, EVENT, INVALID };
    protected ArrayList<Task> tasks;
    protected FileSaver fileSaver;
    protected UndoHistory undoHistory;
    protected RedoHistory redoHistory;
    protected TaskArchive archive;

    private final String BYE_RESPONSE = "Bye! See you soon :)";
    private final String HELP_RESPONSE = "list: list all tasks\ndone {i}: mark task at position {i} as done\n"
            + "delete {i}: delete task at position {i}\nfind {keyword}: find and list all tasks containing {keyword}\n"
            + "todo {description}: creates a new todo\ndeadline {description} /by {date}: creates a new deadline\n"
            + "event {description} /on {date} /from {time} /to {time}: creates a new event\n"
            + "undo: undo previous action\nredo: redo previously undone action\nbye: exit bot";

    /**
     * Constructs a new CommandManager object to process tasks.
     */
    public CommandManager() {
        tasks = new ArrayList<>();
        fileSaver = new FileSaver();
        undoHistory = new UndoHistory();
        redoHistory = new RedoHistory();
        archive = new TaskArchive();
    }

    /**
     * Takes the user input and decides how to handle it.
     * Returns a feedback message to let user know if action has been taken successfully or not.
     *
     * @param input User input.
     * @param tasks Current task list.
     * @return Feedback message.
     */
    public String takeCommand(String input, ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert input != null : "input should not be null";
        assert tasks != null : "tasks should not be null";
        ErrorChecker e = new ErrorChecker(input, tasks);
        CommandType type = categoriseCommand(input);
        assert type != null : "task type should not be null";

        if (!e.isValid()) {
            return e.getMessage();
        }

        switch (type) {
        case UNDO:
            return undoCommand();
        case REDO:
            return redoCommand();
        case BYE:
        case LIST:
        case FIND:
            return executeCommand(input, type);
        default:
            undoHistory.push(input);
            redoHistory.clear();
            return executeCommand(input, type);
        }
    }

    private String executeCommand(String input, CommandType type) {
        switch (type) {
        case BYE:
            return BYE_RESPONSE;
        case HELP:
            return HELP_RESPONSE;
        case LIST:
            return listTasks();
        case DONE:
            return markDone(input);
        case DELETE:
            return deleteTask(input);
        case FIND:
            return findTasks(input);
        case TODO:
        case DEADLINE:
        case EVENT:
            return addNewTask(input, type);
        default:
            return "";
        }

    }

    /**
     * Categorises input into 'help', 'done', 'delete', 'find', 'todo', 'deadline' or 'event'.
     *
     * @param input User input.
     */
    private CommandType categoriseCommand(String input) {
        if (input.equals("bye")) {
            return BYE;
        } else if (input.equals("help")) {
            return HELP;
        } else if (input.equals("list")) {
            return LIST;
        } else if (input.startsWith("done")) {
            return DONE;
        } else if (input.startsWith("delete")) {
            return DELETE;
        } else if (input.startsWith("find")) {
            return FIND;
        } else if (input.equals("undo")) {
            return UNDO;
        } else if (input.equals("redo")) {
            return REDO;
        } else if (input.startsWith("todo")) {
            return TODO;
        } else if (input.startsWith("deadline")) {
            return DEADLINE;
        } else if (input.startsWith("event")) {
            return EVENT;
        } else {
            return INVALID;
        }
    }

    /**
     * Marks task as done.
     *
     * @param input User input.
     * @return Feedback message letting user know task was successfully marked as done.
     */
    private String markDone(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        return "Good job! You got " + task.description + " done!";
    }

    /**
     * Marks task as not done.
     *
     * @param input User input.
     * @return Feedback message letting user know task was successfully marked as done.
     */
    private void unmarkDone(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
        task.unmarkAsDone();
    }

    /**
     * Adds new task to the task list.
     * Saves an updated copy of current task list to local file.
     *
     * @param input User input.
     * @return Feedback message letting user know task was successfully added to task list.
     */
    private String addNewTask(String input, CommandType type) {
        Task newTask;
        String[] inputSplit = input.split("/");

        switch (type) {
        case TODO:
            newTask = new TodoTask(input.substring(5));
            break;
        case DEADLINE:
            String deadlineDescription = inputSplit[0].substring(9, inputSplit[0].length() - 1);
            DateTimeConverter deadlineDateTimeConverter = new DateTimeConverter(inputSplit);
            LocalDate by = deadlineDateTimeConverter.convertDate();

            newTask = new DeadlineTask(deadlineDescription, by);
            break;
        case EVENT:
            String eventDescription = inputSplit[0].substring(6, inputSplit[0].length() - 1);

            DateTimeConverter eventDateTimeConverter = new DateTimeConverter(inputSplit);
            LocalDate on = eventDateTimeConverter.convertDate();
            LocalTime from = eventDateTimeConverter.convertTime("from");
            LocalTime to = eventDateTimeConverter.convertTime("to");

            newTask = new EventTask(eventDescription, on, from, to);
            break;
        default:
            newTask = null;
        }

        tasks.add(newTask);

        if (saveToFile() != "") {
            return saveToFile();
        }

        return "Added: " + newTask.toString();
    }

    /**
     * Deletes task from the task list.
     * Saves an updated copy of current task list to local file.
     *
     * @param input User input.
     * @return Feedback message letting user know task was successfully deleted from task list.
     */
    private String deleteTask(String input) {
        int inputIndex = Integer.parseInt(input.substring(7));
        Task task = tasks.get(inputIndex - 1);
        archive.push(new ArchivedTask(inputIndex, task));
        System.out.println("archive: " + archive);
        tasks.remove(Integer.parseInt(input.substring(7)) - 1);

        if (saveToFile() != "") {
            return saveToFile();
        }

        return "Deleted: " + task.toString();
    }

    private String saveToFile() {
        try {
            fileSaver.saveToFile("DukeList.txt", tasks);
        } catch (IOException ex) {
            return "File path not found: " + ex.getMessage();
        }
        return "";
    }

    /**
     * Finds tasks containing a specified keyword in their description.
     * Tasks are listed with their position in the task list displayed.
     *
     * @param input User input.
     * @return List of tasks containing the keyword in their description.
     */
    private String findTasks(String input) {
        String description = input.substring(5);
        String output = "Here is a list of your tasks that contain " + description + ":";

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(description)) {
                output = output + "\n" + (i + 1) + ". " + tasks.get(i).toString();
            }
        }

        return output;
    }

    /**
     * Lists all tasks in the task list in the order they were added.
     *
     * @return Task list.
     */
    private String listTasks() {
        String output = "Here is a list of your tasks:";

        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }

        return output;
    }

    private String undoCommand() {
        if (!undoHistory.empty()) {
            String previousInput = undoHistory.undo();
            redoHistory.push(previousInput);
            CommandType type = categoriseCommand(previousInput);

            switch (type) {
            case DONE:
                unmarkDone(previousInput);
                break;
            case DELETE:
                ArchivedTask archivedTask = archive.pop();
                tasks.add(archivedTask.getIndex() - 1, archivedTask.getTask());
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                tasks.remove(tasks.size() - 1);
                break;
            default:
                break;
            }

            return "Undid " + previousInput + ".";
        } else {
            return "No task to undo!";
        }
    }

    private String redoCommand() {
        if (!redoHistory.empty()) {
            String nextInput = redoHistory.redo();
            CommandType type = categoriseCommand(nextInput);
            executeCommand(nextInput, type);
            undoHistory.push(nextInput);
            return "Redid " + nextInput + ".";
        } else {
            return "No task to redo!";
        }
    }
}
