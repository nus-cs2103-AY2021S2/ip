import java.util.Scanner;

public class Duke {
    // Divider for Duke's Hello message.
    private static String BORDER = "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-"
            + "+-+-+-+-+-+";

    // Prints a string with a 4-space indent and divider.
    public void formattedPrint(String s) {
        System.out.println("    " + BORDER);
        System.out.println("    " + s);
        System.out.println("    " + BORDER);
    }

    // Greeting message from Duke.
    public void greet() {
        System.out.println(BORDER);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        System.out.println("How may I help you?");
    }
}
