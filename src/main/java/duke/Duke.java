package duke;

import java.util.Arrays;
import java.util.List;

import duke.util.Command;
import duke.util.Deadline;
import duke.util.DukeException;
import duke.util.DukeInputException;
import duke.util.Event;
import duke.util.Pair;
import duke.util.Parser;
import duke.util.SampleData;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Todo;
import duke.util.Ui;

/**
 * Duke is a task manager.
 * <p>
 * Currently supports these functionalities:
 * <br>bye
 * <br>  - Prompt user to save tasklist. Then closes Duke.
 * <br>clear
 * <br>  - Clear all current tasks and start a new tasklist
 * <br>deadline DESCRIPTION /by DATE
 * <br>  - Add a deadline task with a due date (YYYY-MM-DD)
 * <br>delete INT [INT...]
 * <br>  - Delete one or more tasks eg. (delete 1 2 3)
 * <br>done INT [INT...]
 * <br>  - Mark one or more tasks as done eg. (done 1 2 3)
 * <br>event DESCRIPTION /at DATE
 * <br>  - Add an event task with a date (YYYY-MM-DD)
 * <br>help
 * <br>  - Display list of commands
 * <br>highpriority INDEX
 * <br>  - Set this task as high priority
 * <br>list
 * <br>  - List out all tasks
 * <br>load
 * <br>  - Load tasklist from saved file
 * <br>lowpriority INDEX
 * <br>  - Set this task as low priority
 * <br>sample
 * <br>  - Load some sample data
 * <br>save
 * <br>  - save tasklist to "data/dukeData.txt"
 * <br>search {KEYWORD | DATE}
 * <br>  - Display all task containing the following keyword.
 * <br>  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date.
 * <br>sort
 * <br>  - Order tasklist in the following priority
 * <br>    1. High Priority task
 * <br>    2. Incomplete task
 * <br>    3. Todo task
 * <br>    4. Eariler date
 * <br>    5. Lexicographically";
 * <br>todo DESCRIPTION
 * <br>  - Add a todo task
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isWaitingSaveFileResponse = false;
    private boolean isWaitingDeleteTaskResponse = false;
    private boolean isWaitingDeleteAllResponse = false;
    private int[] indexOfTasksToBeDeleted;

    /**
     * Constructor to create Duke object.
     *
     * @param filePath Path to saved file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList();

        try {
            tasks.load(storage.loadTaskList());
        } catch (DukeException e) {
            // Save file missing/invalid so no file loaded
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

        if (isWaitingDeleteAllResponse) {
            return confirmClear(input);
        }

        try {
            return processCommand(Parser.parseInput(input));
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }
    }

    /**
     * Returns TaskList.
     *
     * @return TaskList.
     */
    public TaskList getTaskList() {
        return tasks;
    }

    /**
     * Returns Duke's greeting messages.
     *
     * @return Greeting messages.
     */
    public String displayGreetings() {
        return ui.displayGreetings();
    }

    private String processCommand(Pair<Command, String> instruction) {
        Command command = instruction.getFirst();
        String args = instruction.getSecond();

        if (args.equals("-h")) {
            return command.getHelp();
        }

        try {
            switch(command) {
            case BYE:
                return exit();
            case CLEAR:
                return clearAllTasks();
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
            case HIGHPRIORITY:
                return setHighPriority(args);
            case LIST:
                return listOutTask();
            case LOAD:
                return load();
            case LOWPRIORITY:
                return setLowPriority(args);
            case SAMPLE:
                return loadSampleData();
            case SAVE:
                return save();
            case SEARCH:
                return search(args);
            case SORT:
                return sort();
            case TODO:
                return addTask(Todo.createTodo(args));
            default:
                // Should never reach here
                assert false : "Missed out command in processCommand()";
                return "";
            }
        } catch (DukeException e) {
            return ui.displayError(e);
        }
    }

    private String completeTask(String num) throws DukeInputException {
        // -1 to convert input to zero based
        int[] tasksNum = Arrays.stream(num.split(" "))
                .mapToInt(x -> Integer.parseInt(x) - 1)
                .toArray();

        String[] completedTasks = tasks.completeTask(tasksNum);
        return ui.displayCompletedTask(completedTasks);
    }

    private String addTask(Task task) {
        tasks.addTask(task);
        return ui.displayAddedTask(task.toString(), tasks.size());
    }

    private String deleteTask(String num) {
        // -1 to convert input to zero based
        indexOfTasksToBeDeleted = Arrays.stream(num.split(" "))
                .mapToInt(x -> Integer.parseInt(x) - 1)
                .toArray();
        isWaitingDeleteTaskResponse = true;
        return ui.displayDeleteTaskPrompt(indexOfTasksToBeDeleted.length);
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

    private String setHighPriority(String num) throws DukeInputException {
        // -1 to convert input to zero based
        int taskNum = Integer.parseInt(num) - 1;
        String task = tasks.setPriority(true, taskNum);
        return ui.displaySetPriority(true, task);
    }

    private String setLowPriority(String num) throws DukeInputException {
        // -1 to convert input to zero based
        int taskNum = Integer.parseInt(num) - 1;
        String task = tasks.setPriority(false, taskNum);
        return ui.displaySetPriority(false, task);
    }

    private String loadSampleData() {
        try {
            tasks.load(SampleData.loadSampleData());
        } catch (DukeInputException e) {
            // Should not reach here.
            assert false : "Fix creation of task in SampleData.loadSampleData()";
        }

        return ui.displayLoadSampleMessage();
    }

    private String clearAllTasks() {
        isWaitingDeleteAllResponse = true;
        return ui.displayDeleteAllPrompt();
    }

    private String confirmDelete(String input) {
        boolean isApproved;
        try {
            isApproved = Parser.parseYesNo(input);
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }

        isWaitingDeleteTaskResponse = false;
        if (isApproved) {
            String[] deletedTasks;
            try {
                deletedTasks = tasks.deleteTask(indexOfTasksToBeDeleted);
            } catch (DukeInputException e) {
                return ui.displayError(e);
            }

            return ui.displayDeletedTask(deletedTasks, tasks.size());

        } else {
            return ui.displayDeleteAborted();
        }
    }

    private String confirmClear(String input) {
        boolean isApproved;
        try {
            isApproved = Parser.parseYesNo(input);
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }

        isWaitingDeleteAllResponse = false;
        if (isApproved) {
            tasks.clear();
            return ui.displayTasksClearedMessage();
        } else {
            return ui.displayDeleteAborted();
        }
    }

    private String confirmSave(String input) {
        boolean isApproved;
        try {
            isApproved = Parser.parseYesNo(input);
        } catch (DukeInputException e) {
            return ui.displayError(e);
        }

        isWaitingSaveFileResponse = false;
        if (isApproved) {
            try {
                storage.saveTaskList(tasks.getList());
            } catch (DukeException e) {
                return ui.displayError(e);
            }
        }
        return "shutdownConfirm";
    }
}
