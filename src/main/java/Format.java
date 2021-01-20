public class Format {

    public static final String SPACE = "\n";
    public static final String UPPER = SPACE + "^".repeat(80) + SPACE.repeat(2);
    public static final String LOWER = SPACE.repeat(1) + "_".repeat(80) + SPACE;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String farewell = UPPER
            + "I zao liao, don't miss me."
            + LOWER;
    public static final String greeting = UPPER
            + "Awww, need help ah?"
            + LOWER;

    public static final String chatBox(String s) {
        if (s.length() > 38) {
            return "Walao! Your command too long lah!";
        } else {
            return UPPER + " ".repeat(36 - s.length() / 2) + s + LOWER;
        }
    }

    public static final void LISTING() {
        System.out.println(UPPER);
        for (Task task : Task.getTaskList()) {
            if (task == null) break;
            System.out.println(task);
        }
        System.out.println(LOWER);
    }



}