package duke;

public class Ui {
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "────────────────────────────────────────────────────────────────────────────────";

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }
}
