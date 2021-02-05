package duke.task;

import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import duke.datetime.DateTimeConverter;
import duke.error.ErrorChecker;
import duke.file.FileSaver;

/**
 * Task manager decides what type of action to take based on the user input.
 *
 * @author  Nicole Ang
 */
public class TaskManager {
    protected ArrayList<Task> tasks;
    protected FileSaver fileSaver;

    private boolean isBye;
    private boolean isHelp;
    private boolean isList;
    private boolean isDone;
    private boolean isDelete;
    private boolean isFind;
    private boolean isTodo;
    private boolean isDeadline;
    private boolean isEvent;

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
        ErrorChecker e = new ErrorChecker(input, tasks);
        categoriseTask(input);

        // if (input.equals("help")) {
        //    return HELP_RESPONSE;
        // } else if (input.equals("list")) {
        //    return listEvents();
        // } else if (e.isValid()) {
        //    if (input.startsWith("done")) {
        //        return markDone(input);
        //    } else if (input.startsWith("delete")) {
        //        return deleteTask(input);
        //    } else if (input.startsWith("find")) {
        //        return findTasks(input);
        //    } else {
        //        return addNewTask(input);
        //    }
        // } else {
        //    return e.getMessage();
        // }

        if (isBye) {
            return BYE_RESPONSE;
        } else if (isHelp) {
            return HELP_RESPONSE;
        } else if (isList) {
            return listEvents();
        } else if (e.isValid()) {
            if (isDone) {
                return markDone(input);
            } else if (isDelete) {
                return deleteTask(input);
            } else if (isFind) {
                return findTasks(input);
            } else {
                return addNewTask(input);
            }
        } else {
            return e.getMessage();
        }

    }

    /**
     * Categorises input into 'help', 'done', 'delete', 'find', 'todo', 'deadline' or 'event'.
     *
     * @param input User input.
     */
    public void categoriseTask(String input) {
        isBye = input.equals("bye");
        isHelp = input.equals("help");
        isList = input.equals("list");
        isDone = input.startsWith("done");
        isDelete = input.startsWith("delete");
        isFind = input.startsWith("find");
        isTodo = input.startsWith("todo");
        isDeadline = input.startsWith("deadline");
        isEvent = input.startsWith("event");
    }

    /**
     * Marks task as done.
     *
     * @param input User input.
     * @return Feedback message letting user know task was successfully marked as done.
     */
    public String markDone(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        return "Good job! You got " + task.description + " done!";
    }

    /**
     * Adds new task to the task list.
     * Saves an updated copy of current task list to local file.
     *
     * @param input User input.
     * @return Feedback message letting user know task was successfully added to task list.
     */
    public String addNewTask(String input) {
        Task newTask;
        if (isTodo) {
            newTask = new TodoTask(input.substring(5));
        } else if (isDeadline) {
            String[] inputSplit = input.split("/");
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate());
        } else {
            String[] inputSplit = input.split("/");
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate(), dateTimeConverter.convertTime("from"),
                    dateTimeConverter.convertTime("to"));
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
    public String deleteTask(String input) {
        Task task = tasks.get(Integer.parseInt(input.substring(7)) - 1);
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
    public String findTasks(String input) {
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
    public String listEvents() {
        String output = "Here is a list of your tasks:";

        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }

        return output;
    }
}
