public class Format {

    public static final String SPACE = "\n";
    public static final String UPPER = SPACE + "^".repeat(80) + SPACE.repeat(2);
    public static final String LOWER = SPACE.repeat(2) + "_".repeat(80) + SPACE;



    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String farewell = UPPER
            + "Good friends never say goodbye. They simply say ‘See you soon'."
            + LOWER;

    public static final String greeting = UPPER
            + "What's up! This is Duke, what do you want to do today!"
            + LOWER;

    public static final String chatBox(String s) {
        if (s.length() > 35) {
            int mid = s.length() / 2;
            return UPPER + " ".repeat(40 - mid / 2) + s.substring(0, mid)
                    + SPACE + " ".repeat(40 - mid / 2) + s.substring(mid) + LOWER;
        } else if (s.length() >= 60) {
            return "ERROR! Your command is too long!";
        } else {
            return UPPER + " ".repeat(38 - s.length() / 2) + s + LOWER;
        }

    }





}