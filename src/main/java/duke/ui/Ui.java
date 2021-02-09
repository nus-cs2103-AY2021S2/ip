package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that store all the possible String format for Duke for code simplicity.
 */
public class Ui {

    public static final String LINEBREAK = "\n";
    public static final String LOGO = "D U K E";
    public static final String WRONG_DATE_FORMAT = "!!!Err, wrong date format.. (yyyy-mm-dd)!!!";
    public static final String KEY_IN_NUMBER = "!!!!!PLease Lah! Key in number!!!!!!";
    public static final String EMPTY_TASK = "!!!Walao!NO TASK!!!";
    public static final String MISSING_DATE = "Fill ur date lah (add date with / in yyyy-mm-dd format)!";
    public static final String COMMAND_ERROR = "!!I DON'T KNOW WHAT U SAYING BRO!!";
    public static final String TASK_ERROR = "!!!!!!!!!!Walao, no such task!!!!!!!!!";
    public static final String SAVE_TO_FILE_ERROR = "Huh? Where your file?";
    public static final String EMPTY_COMMAND = "!!!Walao, command cannot be empty!!!";
    public static final String SUCCESSFUL_SAVE = "~File Saved Successfully!~";
    public static final String FAREWELL = " I Zao Liao. Don't Miss Meeeeeee.";
    public static final String GREETING = "** Awww, need help ah? **";
    public static final String EMPTY_FILE = "*Awwww~ You don't have any history of tasks *";
    public static final String SUCESSFUL_LOAD = "*Sir, here is your past history: *\n";

    /**
     * Display a message to indicate the given task as done.
     * @param t the task to be displayed as done.
     */
    public static String doneTask(Task t) {

        return "Wah~ You done the task: "
                + " " + t.toString();
    }

    /**
     * Display a message to indicate the given task as deleted.
     * @param t the task to be displayed as deleted.
     */
    public static String deleteTask(Task t) {

        return "Awww~ You've deleted the task: "
                + " " + t.toString();
    }

    /**
     * make a Bigger ChatBox that wrap the name of a given Task.
     *
     * @param t task to be wrapped.
     * @return the String representation of the task name wrapped in a chatBox.
     */
    public static String biggerBox(Task t) {
        return "Added liao: "
            + t.toString() + Ui.LINEBREAK
            + "You have " + TaskList.getTasksSize() + " tasks in the list!";
    }

}

