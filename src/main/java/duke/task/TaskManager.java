package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.datetime.DateTimeConverter;
import duke.error.ErrorChecker;
import duke.error.IllegalTaskException;
import duke.file.FileSaver;
import duke.history.RedoHistory;
import duke.history.TaskArchive;
import duke.history.UndoHistory;

import static duke.task.TaskManager.CommandType.*;

/**
 * Task manager decides what type of action to take based on the user input.
 *
 * @author  Nicole Ang
 */
public class TaskManager {
    public enum CommandType { BYE, HELP, LIST, DONE, DELETE, FIND, UNDO,
        REDO, TODO, DEADLINE, EVENT, INVALID };
    protected ArrayList<Task> tasks;
    protected FileSaver fileSaver;
    protected UndoHistory undoHistory;
    protected RedoHistory redoHistory;
    protected TaskArchive archive;

//    private boolean isBye;
//    private boolean isHelp;
//    private boolean isList;
//    private boolean isDone;
//    private boolean isDelete;
//    private boolean isFind;
//    private boolean isUndo;
//    private boolean isRedo;
//    private boolean isTodo;
//    private boolean isDeadline;
//    private boolean isEvent;
//    private boolean isNewTask;

    private final String BYE_RESPONSE = "Bye! See you soon :)";
    private final String HELP_RESPONSE = "list: list all tasks\ndone {i}: mark task at position {i} as done\n"
            + "delete {i}: delete task at position {i}\nfind {keyword}: find and list all tasks containing {keyword}\n"
            + "todo {description}: creates a new todo\ndeadline {description} /by {date}: creates a new deadline\n"
            + "event {description} /on {date} /from {time} /to {time}: creates a new event";

    /**
     * Constructs a new TaskManager object to process tasks.
     */
    public TaskManager() {
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
    public String takeEvent(String input, ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert input != null : "input should not be null";
        assert tasks != null : "tasks should not be null";
        ErrorChecker e = new ErrorChecker(input, tasks);
        CommandType type = categoriseTask(input);

        if (!e.isValid()) {
            return e.getMessage();
        }

        switch (type) {
        case UNDO:
            return undoInput();
        case REDO:
            return redoInput();
        case BYE:
        case LIST:
        case FIND:
            return executeCommand(input, type);
//        case TODO:
//        case DEADLINE:
//        case EVENT:
//            undoHistory.push(input);
//            System.out.println("push onto undoHistory");
//            System.out.println("undo history: " + undoHistory);
//            redoHistory.clear();
//            return executeCommand(input, type);
        default:
            undoHistory.push(input);
            System.out.println("push onto undoHistory");
            System.out.println("undo history: " + undoHistory);
            redoHistory.clear();
            return executeCommand(input, type);
        }


//        if (isUndo) {
//            return undoInput();
//        } else if (isRedo) {
//            return redoInput();
//        } else {
//            undoHistory.push(input);
//            System.out.println("push onto undoHistory");
//            System.out.println("undo history: " + undoHistory);
//            redoHistory.clear();
//            return executeCommand(input, type);
//        }

//        if (isBye) {
//            return BYE_RESPONSE;
//        } else if (isHelp) {
//            return HELP_RESPONSE;
//        } else if (isList) {
//            return listEvents();
//        } else if (isDone) {
//            return markDone(input);
//        } else if (isDelete) {
//            return deleteTask(input);
//        } else if (isFind) {
//            return findTasks(input);
//        } else if (isNewTask) {
//            return addNewTask(input);
//        } else {
//            return e.getMessage();
//        }
//        return "";

    }

    private String executeCommand(String input, CommandType type) {
        switch (type) {
        case BYE:
            return BYE_RESPONSE;
        case HELP:
            return HELP_RESPONSE;
        case LIST:
            return listEvents();
        case DONE:
            return markDone(input);
//        case UNDONE:
//            return unmarkDone();
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
    private CommandType categoriseTask(String input) {
//        isBye = input.equals("bye");
//        isHelp = input.equals("help");
//        isList = input.equals("list");
//        isDone = input.startsWith("done");
//        isDelete = input.startsWith("delete");
//        isFind = input.startsWith("find");
//        isUndo = input.equals("undo");
//        isRedo = input.equals("redo");
//        isTodo = input.startsWith("todo");
//        isDeadline = input.startsWith("deadline");
//        isEvent = input.startsWith("event");
//        isNewTask = isTodo || isDeadline || isEvent;

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
    private String unmarkDone(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
        task.unmarkAsDone();
//        return "Good job! You got " + task.description + " done!";
        return "";
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

//        if (isTodo) {
//            newTask = new TodoTask(input.substring(5));
//        } else if (isDeadline) {
//            String description = inputSplit[0].substring(9, inputSplit[0].length() - 1);
//            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
//            LocalDate by = dateTimeConverter.convertDate();
//
//            newTask = new DeadlineTask(description, by);
//        } else if (isEvent) {
//            String description = inputSplit[0].substring(6, inputSplit[0].length() - 1);
//
//            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
//            LocalDate on = dateTimeConverter.convertDate();
//            LocalTime from = dateTimeConverter.convertTime("from");
//            LocalTime to = dateTimeConverter.convertTime("to");
//
//            newTask = new EventTask(description, on, from, to);
//        } else {
//            throw(new IllegalTaskException("", ""));
//        }

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

        try {
            fileSaver.saveToFile("DukeList.txt", tasks);
        } catch (IOException ex) {
            return "File path not found: " + ex.getMessage();
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
        Task task = tasks.get(Integer.parseInt(input.substring(7)) - 1);
        archive.push(task);
        System.out.println("archive: " + archive);
        tasks.remove(Integer.parseInt(input.substring(7)) - 1);

        try {
            fileSaver.saveToFile("DukeList.txt", tasks);
        } catch (IOException ex) {
            return "File path not found: " + ex.getMessage();
        }

        return "Deleted: " + task.toString();
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
    private String listEvents() {
        String output = "Here is a list of your tasks:";

        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }

        return output;
    }

    private String undoInput() {
        if (!undoHistory.empty()) {
            String previousInput = undoHistory.undo();
            redoHistory.push(previousInput);
            System.out.println("push onto redoHistory");
            System.out.println("redohist: " + redoHistory);

            CommandType type = categoriseTask(previousInput);

//            public enum CommandType { BYE, HELP, LIST, DONE, DELETE, FIND, UNDO,
//                REDO, TODO, DEADLINE, EVENT, INVALID };

            switch (type) {
            case DONE:
                unmarkDone(previousInput);
                break;
            case DELETE:
                System.out.println(archive);
                tasks.add(archive.pop());
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                tasks.remove(tasks.size() - 1);
                break;
            default:
                break;
            }

//            executeCommand(previousInput);
            return "Undid " + previousInput;
        } else {
            return "No task to undo!";
        }
    }

    private String redoInput() {
        if (!redoHistory.empty()) {
            String nextInput = redoHistory.redo();
            CommandType type = categoriseTask(nextInput);
            executeCommand(nextInput, type);
            return "Redid " + nextInput;
        } else {
            return "No task to redo!";
        }
    }
}
