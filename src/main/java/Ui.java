public class Ui {
    private static final String LINE = "----------------------------------------------\n";
    private static final String GREETING = LINE
            + "Hello! I`m Duke\n"
            + "Please enter file name to load tasks\n "
            + LINE;
    private static final String START = "File loaded, what do you want to do? ";

    private static TaskList tasks;
    private static Storage storage;

    /**
     * Display greeting messages
     */
    public static String showGreeting() {
        return GREETING;
    }

    public static String load(String fileName) {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load(fileName));
        } catch (DukeException e) {
            return showError(e.getMessage());
        } catch (DukeDeadlineException e) {
            return showError(e.getMessage());
        }
        return START;
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
        return message + printExecuteResult(message, task, numTasks, "");
    }

    /**
     * Shows success message upon deleting a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     * @return String , message to be printed on GUI
     */
    public static String showSuccessDeleteTask(String task, int numTasks) {
        String message = "Noted. I`ve removed this task: \n";
        return message + printExecuteResult(message, task, numTasks, "");
    }

    /**
     * Shows success message upon adding a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     * @return String , message to be printed on GUI
     */
    public static String showSuccessAddTask(String task, int numTasks) {
        String message = "Got it. I`ve added this task: \n";
        return message + printExecuteResult(message, task, numTasks, "");
    }

    public static String showSuccessSearch() {
        return "Here are the matching tasks in your list:";
    }

    public static String showFailSearch(String searchTerm) {
        return "There are no matching task with " + searchTerm;
    }

    public static String parseAndPrint(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            return null;
            //return command.excute();
        } catch (DukeException e) {
            return showError(e.getMessage());
        } catch (DukeDeadlineException e) {
            return showError(e.getMessage());
        } catch (NullPointerException e) {
            return showError("OOPS!!! I`m sorry. but i don`t know what that means :-(");
        }
    }
}
