package duke.ui;

import duke.task.Task;

/**
 * A class that store all the possible String format for Duke for code simplicity.
 */
public class Ui {

    public static final String LINEBREAK = "\n";
    public static final String UPPER = LINEBREAK + "^".repeat(90) + LINEBREAK.repeat(2);
    public static final String LOWER = LINEBREAK.repeat(1) + "_".repeat(90) + LINEBREAK;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String WRONG_DATE_FORMAT = "!!!Err, wrong date format.. (yyyy-mm-dd)!!!";
    public static final String KEY_IN_NUMBER = "!!!!!!!PLease Lah! Key in number!!!!!!!!";
    public static final String EMPTY_TASK = "!!!!!!!!!Walao!NO TASK!!!!!!!!!!";
    public static final String MISSING_DATE = "!!!Fill ur date lah (add date with / in yyyy-mm-dd format)!!!";
    public static final String COMMAND_ERROR = "!!!!!!!I DON'T KNOW WHAT U SAYING BRO!!!!!!!";
    public static final String TASK_ERROR = "!!!!!!!!!!Walao, no such task!!!!!!!!!";
    public static final String SAVE_TO_FILE_ERROR = "Huh? Where your file?";
    public static final String EMPTY_COMMAND = "!!!Walao, command cannot be empty!!!";
    public static final String SUCCESSFUL_SAVE = "~~~~~~~~~~~File Saved Successfully!~~~~~~~~~~~~";
    public static final String FAREWELL = UPPER
            + "~~~~~~~~~~~ I Zao Liao. Don't Miss Meeeeeee ~~~~~~~~~~~."
            + LOWER;
    public static final String GREETING = UPPER
            + "****************** Awww, need help ah? ******************"
            + LOWER;

    public static final String NO_DIRECTORY = "********** Cannot find your directory eh, "
            + "first time ah? Create one for you **********\n" ;
    public static final String LOAD_FILE = "********** Wait ah~ Loading file for you **********\n";
    public static final String NO_FILE = "********** File also don't have ah, nvm I make one for you **********\n";
    public static final String EMPTY_FILE = "********** Awwww~ You don't have any history of tasks **********\n";
    public static final String SUCESSFUL_LOAD = "***********Sir, here is your past history************";



    public static void doneTask(Task t) {
        System.out.println(Ui.UPPER + "Wah~ You done the task: "
                + " " + t.toString() + Ui.LOWER);
    }
    public static void deleteTask(Task t) {
        System.out.println(Ui.UPPER + "Awww~ You've deleted the task: "
                + " " + t.toString() + Ui.LOWER);
    }

    /**
     * make a Bigger ChatBox that wrap the name of a given Task.
     * @param t task to be wrapped.
     * @return the String representation of the task name wrapped in a chatBox.
     */
    public static String biggerBox(Task t) {
        return Ui.UPPER + "Added liao: "
                + t.toString() + Ui.LINEBREAK
                + "You have " + Task.getCapacity() + " tasks in the list!"
                + Ui.LOWER;
    }

    /**
     * Display all the current task and status store in the tasklist.
     *
     */
    public static void LISTING() {
        System.out.println(UPPER);
        for (Task task : Task.getTaskList()) {
            if (task == null) break;
            System.out.println(task);
        }
        System.out.println(LOWER);
    }

    public static void DONELOADING() {
        System.out.println(UPPER);
        for (Task task : Task.getTaskList()) {
            if (task == null) break;
            System.out.println(task);
        }
        System.out.println("Sir, here is your past history");
        System.out.println(LOWER);
    }

    public static void SLEEP() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}