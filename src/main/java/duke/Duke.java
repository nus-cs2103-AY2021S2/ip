package duke;

import java.util.Arrays;
import java.util.List;

import duke.util.Command;
import duke.util.Deadline;
import duke.util.DukeException;
import duke.util.DukeInputException;
import duke.util.Event;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Todo;
import duke.util.Ui;
import javafx.collections.ObservableList;

/**
 * Duke is a task manager.
 * <p>
 * Currently supports these functionalities:
 * <br>bye
 * <br>  - Prompt user to save tasklist. Then closes Duke.
 * <br>deadline [description] /by [date]
 * <br>  - Add a deadline task with a due date (YYYY-MM-DD)
 * <br>delete [int (int int...)]
 * <br>  - Delete one or more tasks eg. (delete 1 2 3)
 * <br>done [int (int int...)]
 * <br>  - Mark one or more tasks as done eg. (done 1 2 3)
 * <br>event [description] /at [date]
 * <br>  - Add an event task with a date (YYYY-MM-DD)
 * <br>help
 * <br>  - Display list of commands
 * <br>list
 * <br>  - List out all tasks
 * <br>load
 * <br>  - Load tasklist from saved file
 * <br>save
 * <br>  - save tasklist to "data/dukeData.txt"
 * <br>search [keyword | date]
 * <br>  - Display all task containing the following keyword.
 * <br>  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date.
 * <br>sort
 * <br>  - Order tasklist in the following priority
 * <br>    1. Incomplete task
 * <br>    2. Todo task
 * <br>    3. Eariler date
 * <br>    4. lexicographically";
 * <br>todo [description]
 * <br>  - Add a todo task
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isWaitingSaveFileResponse = false;
    private boolean isWaitingDeleteTaskResponse = false;
    private int[] tasksToBeDeleted;

    /**
     * Constructor to create Duke object.
     *
     * @param filePath File path to save tasklist.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList();

        try {
            tasks.load(storage.loadTaskList());
        } catch (DukeException e) {
            // No file loaded..
        }
    }

    /**
     * Processes user input and provides response.
     *
     * @param input User input.
     * @return Duke response.
     */
    public String getResponse(String input) {

        if (isWaitingSaveFileResponse) {
            return confirmSave(input);
        }

        if (isWaitingDeleteTaskResponse) {
            return confirmDelete(input);
        }

        try {
            Parser.parseInput(input);
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }

        String[] s = input.split(" ", 2);
        Command cmd = Command.valueOf(s[0].toUpperCase());
        String args = s.length == 2 ? s[1] : "";

        return processCommand(cmd, args);
    }

    private String completeTask(String num) throws DukeInputException {

        int[] tasksNum = Arrays.stream(num.split(" "))
              .mapToInt(x -> Integer.parseInt(x) - 1)
              .toArray();

        String[] completedTasks = tasks.completeTask(tasksNum);
        return ui.completeTask(completedTasks);
    }

    private String addTask(Task t) {
        tasks.addTask(t);
        return ui.addTask(t.toString(), tasks.size());
    }

    private String confirmDelete(String s) {
        try {
            Parser.parseYesNo(s);
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }
        assert s.equals("y") || s.equals("n") : "Parser.parseYesNo() allowed invalid input";
        isWaitingDeleteTaskResponse = false;

        if (s.equals("y")) {
            String[] deletedTasks;

            try {
                deletedTasks = tasks.deleteTask(tasksToBeDeleted);
            } catch (DukeInputException e) {
                return ui.displayError(e);
            }
            return ui.deleteTask(deletedTasks, tasks.size());

        } else {
            return ui.abortDelete();
        }
    }

    private String confirmSave(String s) {
        try {
            Parser.parseYesNo(s);
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }
        assert s.equals("y") || s.equals("n") : "Parser.parseYesNo() allowed invalid input";

        isWaitingSaveFileResponse = false;
        if (s.equals("y")) {
            try {
                storage.saveTaskList(tasks.getList());
            } catch (DukeException e) {
                return ui.displayError(e);
            }
        }
        return "shutdownConfirm";
    }

    private String deleteTask(String num) {
        tasksToBeDeleted = Arrays.stream(num.split(" "))
                .mapToInt(x -> Integer.parseInt(x) - 1)
                .toArray();
        isWaitingDeleteTaskResponse = true;
        return ui.displayDeleteTaskPrompt(tasksToBeDeleted.length == 1);
    }

    private String load() throws DukeException {
        tasks.load(storage.loadTaskList());
        return ui.displayLoadMessage();
    }

    private String save() throws DukeException {
        storage.saveTaskList(tasks.getList());
        return ui.displaySaveMessage();
    }

    private String exit() {
        isWaitingSaveFileResponse = true;
        return ui.displaySaveFilePrompt();
    }

    private String listOutTask() {
        List<String> lst = tasks.listOutTask();
        return ui.displayList(lst);
    }

    private String displayHelp() {
        return ui.displayHelp();
    }

    private String search(String args) {
        List<String> results = tasks.search(args);
        return ui.displayList(results);
    }

    private String sort() {
        tasks.sort();
        return ui.displaySortMessage();
    }

    private String processCommand(Command command, String args) {
        if (args.equals("-h")) {
            return command.getHelp();
        }

        try {
            switch(command) {
            case BYE:
                return exit();
            case DEADLINE:
                return addTask(Deadline.createDeadline(args));
            case DELETE:
                return deleteTask(args);
            case DONE:
                return completeTask(args);
            case EVENT:
                return addTask(Event.createEvent(args));
            case HELP:
                return displayHelp();
            case LIST:
                return listOutTask();
            case LOAD:
                return load();
            case SAVE:
                return save();
            case SEARCH:
                return search(args);
            case SORT:
                return sort();
            case TODO:
                return addTask(Todo.createTodo(args));
            default:
                break;
            }
        } catch (DukeException e) {
            return ui.displayError(e);
        }
        // Should never reach here
        throw new RuntimeException("ERROR in Duke's getResponse method");
    }

    /**
     * Returns TaskList.
     *
     * @return TaskList.
     */
    protected ObservableList<Task> getTaskList() {
        return tasks.getList();
    }

    /**
     * Returns Duke's greeting messages.
     *
     * @return Greeting messages.
     */
    public String displayGreetings() {
        return ui.displayGreetings();
    }
}
