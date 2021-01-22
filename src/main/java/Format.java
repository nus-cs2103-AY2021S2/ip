/**
 * A class that store all the possible String format for Duke for code simplicity.
 */
public class Format {

    public static final String LINEBREAK = "\n";
    public static final String UPPER = LINEBREAK + "^".repeat(90) + LINEBREAK.repeat(2);
    public static final String LOWER = LINEBREAK.repeat(1) + "_".repeat(90) + LINEBREAK;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WRONGDATEFORMAT = "Err, wrong date format..";
    public static final String KEYINNUMBER = "PLease Lah! Key in number!";
    public static final String EMPTYTASK = "Walao!NO TASK!";
    public static final String TOOMANYARGUMENTS = "You put so many/few ARGUMENTS for what";
    public static final String MISSINGDATE = "Fill ur date lah";
    public static final String COMMANDERROR = "I DON'T KNOW WHAT U SAYING BRO";
    public static final String TASKERROR = "Walao, no such task";
    public static final String FAREWELL = UPPER
            + "I zao liao, don't miss me."
            + LOWER;
    public static final String GREETING = UPPER
            + "Awww, need help ah?"
            + LOWER;

    /**
     * make a chatBox that wrap a given String.
     * @param s the String to be wrapped.
     * @return a String with a chatBox wrapped.
     */
    public static final String chatBox(String s) {
        if (s.length() > 50) {
            return "Walao! Your command too long lah!";
        } else {
            return UPPER + " ".repeat(36 - s.length() / 2) + s + LOWER;
        }
    }


    /**
     * make a Bigger ChatBox that wrap the name of a given Task.
     * @param t task to be wrapped.
     * @return the String representation of the task name wrapped in a chatBox.
     */
    public static final String biggerBox(Task t) {
        return Format.UPPER + "Added liao: "
                + t.toString() + Format.LINEBREAK
                + "You have " + Task.getCapacity() + " tasks in the list!"
                + Format.LOWER;
    }

    /**
     * Display all the current task and status store in the tasklist.
     *
     */
    public static final void LISTING() {
        System.out.println(UPPER);
        for (Task task : Task.getTaskList()) {
            if (task == null) break;
            System.out.println(task);
        }
        System.out.println(LOWER);
    }



}