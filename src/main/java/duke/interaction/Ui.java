package duke.interaction;

import duke.common.DukeString;

public class Ui {
    private Ui() { };

    public static void printOut(final String msg) {
        System.out.println(DukeString.SEPARATOR);
        System.out.println(msg);
        System.out.println(DukeString.SEPARATOR);
    }

    public static void printErr(final String msg) {
        System.out.println(DukeString.SEPARATOR_ERR);
        System.out.println(msg);
        System.out.println(DukeString.SEPARATOR_ERR);
    }
}
