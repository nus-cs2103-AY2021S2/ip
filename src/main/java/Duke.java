
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(greetingMessage);

        chatLoop(System.in, System.out);
    }

    public static String greetingMessage = "Hello! I'm Duke\n" + "What can I do for you?";

    public static void chatLoop(InputStream in, OutputStream out) throws IOException {
        BufferedReader reader = getReader(in);
        PrintStream writer = getWriter(out);
        String line = reader.readLine();
        while (line != null) {
            writer.println(parseInput(line));
            if (line.equals("bye")) {
                break;
            }
            line = reader.readLine();
        }
    }

    private static PrintStream getWriter(OutputStream out) {
        return new PrintStream(out);
    }

    private static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    public static String parseInput(String input) {
        switch (input) {
            case "bye":
                return "Bye. Hope to see you again soon!";
            default:
                return input;
        }
    }

}
