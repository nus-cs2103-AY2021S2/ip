public class Ui {
    public static final String GOODBYE = "Thank you for using Duke , please come back again";

    private static final String GREETING = "Hello! I`m Duke\n"
            + "Please enter file name to load tasks\n ";
    private static final String FAILURE_LOAD_START = "No task is found in file, what can i do for you? ";
    private static final String FAILURE_UPDATE = "Field to update is invalid";
    private static final String FAILURE_SEARCH = "There are no matching task with ";

    private static final String SUCCESS_LOAD_START = "File loaded, what can i do for you? ";
    private static final String SUCCESS_UPDATE = "Task is updated successfully";
    private static final String SUCCESS_MARK_DONE = "Got it. I`ve mark this task as done:";
    private static final String SUCCESS_DELETE_TASK = "Noted. I`ve removed this task: \n";
    private static final String SUCCESS_ADD_TASK = "Got it. I`ve added this task: \n";

    private static final String SUCCESS_SEARCH = "Here are the matching tasks in your list:";
    private static final String HELP_MESSAGE = "Example commands: \n"
            + "bye \n"
            + "list \n"
            + "done <number> \n"
            + "delete <number> \n"
            + "find <search key> \n"
            + "todo <taskName> \n"
            + "deadline <taskName> /by yyyy-M-dd Hmm \n"
            + "event <taskName> /by yyyy-M-dd Hmm - Hmm \n"
            + "update <number> <fieldName> <value> \n";


    private TaskList tasks;
    private Storage storage;

    /**
     * Display greeting messages
     */
    public static String showGreeting() {
        return GREETING;
    }

    public static String showGoodbye() {
        return GOODBYE;
    }

    /**
     * Loads tasks from the fileName provided.
     * @param fileName (file to load tasks from)
     * @return message to indicate success / failure
     */
    public String load(String fileName) {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load(fileName));
        } catch (DukeException e) {
            return showError(e.getMessage());
        } catch (DukeDeadlineException e) {
            return showError(e.getMessage());
        }
        String result = SUCCESS_LOAD_START;
        if (tasks.getTaskList().size() == 0) {
            result = FAILURE_LOAD_START;
        }

        return result + "\n" + HELP_MESSAGE;
    }

    /**
     * Shows error message provided
     * @param error error message
     */
    public static String showError(String error) {
        return error;
    }

    /**
     * Prints the representation of task with a numbering
     * @param numbering
     * @param task
     * @return String , message to be printed on GUI
     */
    public static String printTask(String numbering, String task) {
        return String.format("%2s %s\n", numbering, task);
    }

    private static String printExecuteResult(String message, String task , int numTasks, String numbering) {
        String output = message + "\n" + printTask(numbering, task);
        return String.format("%s\nNow you have %d task in the list\n", output , numTasks);
    }

    /**
     * Shows success messages upon marking a task done
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     */
    public static String showSuccessMarkDone(String task, int numTasks) {
        return printExecuteResult(SUCCESS_MARK_DONE, task, numTasks, "");
    }

    /**
     * Shows success message upon deleting a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     * @return String , message to be printed on GUI
     */
    public static String showSuccessDeleteTask(String task, int numTasks) {
        return printExecuteResult(SUCCESS_DELETE_TASK, task, numTasks, "");
    }

    /**
     * Shows success message upon adding a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     * @return String , message to be printed on GUI
     */
    public static String showSuccessAddTask(String task, int numTasks) {
        return printExecuteResult(SUCCESS_ADD_TASK, task, numTasks, "");
    }

    public static String showSuccessSearch() {
        return SUCCESS_SEARCH;
    }

    public static String showFailSearch(String searchTerm) {
        return FAILURE_SEARCH + searchTerm;
    }

    public static String showSuccessUpdate() {
        return SUCCESS_UPDATE;
    }

    public static String showFailUpdate() {
        return FAILURE_UPDATE;
    }

    public void save() {
        storage.save(tasks.getTaskList());
    }

    /**
     * Main logic for Duke program.
     * @param fullCommand , command to be executed
     * @return display message for command received
     */
    public String parseAndPrint(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            //Ensure that command is not null so that execute will work
            assert command != null;
            return command.execute(tasks, this);
        } catch (DukeException e) {
            return showError(e.getMessage());
        } catch (DukeDeadlineException e) {
            return showError(e.getMessage());
        }
    }
}
