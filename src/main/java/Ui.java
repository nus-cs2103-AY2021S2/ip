public class Ui {
    static final String LINE_AFTER_COMMAND = "___________________________________" +
            "_________________________";

    public static void showMessage(String message) {
        System.out.println(message);
        System.out.println(LINE_AFTER_COMMAND);
    }

    public static void showMessageInALine(String item) {
        System.out.println(item);
    }

    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE_AFTER_COMMAND + "\nHello! I'm  Duke");
        System.out.println("What can I do for you?\n" + LINE_AFTER_COMMAND + "\n");
    }

    public static void doBye() {
        System.out.println(LINE_AFTER_COMMAND + "\nBye. Hope to see you again soon!\n"
                + LINE_AFTER_COMMAND + "\n");
    }

    public static void printLine() {
        System.out.println(LINE_AFTER_COMMAND);
    }

    public static void finishTaskMessage(String message) {
        Ui.showMessageInALine("Nice! I've marked this task as done: ");
        System.out.println(" " + message);
        System.out.println(LINE_AFTER_COMMAND);
    }

    public static void deleteTaskMessage(String message, int newSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + message);
        System.out.println("Now you have " + newSize + " tasks in the list.");
        System.out.println(LINE_AFTER_COMMAND + "\n");
    }

    public static void addTaskMessage(String message, int newSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + message);
        System.out.println("Now you have " + newSize + " tasks in the list.");
        System.out.println(LINE_AFTER_COMMAND + "\n");
    }
}
