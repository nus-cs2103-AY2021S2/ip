import java.io.OutputStream;
import java.io.PrintWriter;

public class UI {
    private static final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String greetingMessage = "Hello! I'm Duke\n" + "What can I do for you?";

    /**
     * Sends the greeting message
     */
    public static void greet(OutputStream outs) {
        PrintWriter out = new PrintWriter(outs);
        out.println("Hello from");
        out.println();
        out.println(logo);
        out.println(greetingMessage);
    }
}
