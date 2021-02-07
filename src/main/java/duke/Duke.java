package duke;

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
 * <br>list
 * <br>  - List out all task
 * <br>done [number]
 * <br>  - Mark selected task as done
 * <br>todo [description]
 * <br>  - Add a todo task
 * <br>deadline [description] /by [due date]
 * <br>  - Add a deadline task with a due date (YYYY-MM-DD)
 * <br>event [description] /at [date]
 * <br>  - Add a event task with a date (YYYY-MM-DD)
 * <br>delete [number]
 * <br>  - Delete a task
 * <br>save
 * <br>  - save checklist to "data/dukeData.txt"
 * <br>load
 * <br>  - Load previously saved checklist
 * <br>help
 * <br>  - Display list of commands
 * <br>search [keyword/date]
 * <br>  - Display all task containing the following keyword.
 * <br>  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isWaitingSaveFileResponse = false;
    private boolean isWaitingDeleteTaskResponse = false;
    private int taskToBeDeleted;

    /**
     * Constructor to create Duke object.
     *
     * @param filePath File path to save tasklist.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            tasks = new TaskList();
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
        String command = s[0];
        String args = s.length == 2 ? s[1] : "";

        try {
            switch (command) {
            case "bye":
                isWaitingSaveFileResponse = true;
                return ui.saveFilePrompt();
            case "list":
                return ui.displayList(tasks.listOutTask());
            case "done":
                return completeTask(args);
            case "todo":
                return addTask(Todo.createTodo(args));
            case "deadline":
                return addTask(Deadline.createDeadline(args));
            case "event":
                return addTask(Event.createEvent(args));
            case "delete":
                taskToBeDeleted = Integer.parseInt(args);
                isWaitingDeleteTaskResponse = true;
                return ui.deleteTaskPrompt();
            case "save":
                storage.saveTaskList(tasks.getList());
                return ui.displaySaveMessage();
            case "load":
                tasks = new TaskList(storage.loadTaskList());
                return ui.displayLoadMessage();
            case "help":
                return ui.help();
            case "search":
                return ui.displayList(tasks.search(args));
            default:
                assert false : "Parser missed an invalid input";
            }
        } catch (DukeException e) {
            return ui.displayError(e);
        }

        throw new RuntimeException("ERROR in Duke's getResponse method"); // Should never reach here;
    }

    private String completeTask(String num) throws DukeInputException {
        int taskNum = Integer.parseInt(num);
        Task t = tasks.completeTask(taskNum - 1);
        return ui.completeTask(t.toString());
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
            Task t;
            try {
                t = tasks.deleteTask(taskToBeDeleted - 1);
            } catch (DukeInputException e) {
                return ui.displayError(e);
            }
            return ui.deleteTask(t.toString(), tasks.size());
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
