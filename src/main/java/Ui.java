public class Ui {
    private static final String GREETING = "Hello! I`m Duke\n"
            + "Please enter file name to load tasks\n ";
    private static final String GOODBYE = "Thank you for using Duke , please come back again";
    private static final String FAILURE_LOAD_START = "No task is found in file, what can i do for you? ";
    private static final String SUCCESS_LOAD_START = "File loaded, what can i do for you? ";

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

        if (tasks.getTaskList().size() == 0) {
            return FAILURE_LOAD_START;
        }

        return SUCCESS_LOAD_START;
    }

    /**
     * Reads a file path from the user
     * @return fileName entered by the user;
     */
    public static String askFilePath() {
        return "Please enter file name : ";
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
        String message = "Got it. I`ve mark this task as done:";
        return printExecuteResult(message, task, numTasks, "");
    }

    /**
     * Shows success message upon deleting a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     * @return String , message to be printed on GUI
     */
    public static String showSuccessDeleteTask(String task, int numTasks) {
        String message = "Noted. I`ve removed this task: \n";
        return printExecuteResult(message, task, numTasks, "");
    }

    /**
     * Shows success message upon adding a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     * @return String , message to be printed on GUI
     */
    public static String showSuccessAddTask(String task, int numTasks) {
        String message = "Got it. I`ve added this task: \n";
        return printExecuteResult(message, task, numTasks, "");
    }

    public static String showSuccessSearch() {
        return "Here are the matching tasks in your list:";
    }

    public static String showFailSearch(String searchTerm) {
        return "There are no matching task with " + searchTerm;
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
