
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    public static ArrayList<String> tasks;

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(greetingMessage);

        tasks = new ArrayList<>(100);

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

    public static String parseInput(String input) {
        switch (input) {
            case "bye":
                return "Bye. Hope to see you again soon!";
            case "list": {
                String output = "";
                for (int i = 0; i < tasks.size(); i++) {
                    output += String.format("%d. %s\n", i + 1, tasks.get(i));
                }
                return output;
            }
            default:
                tasks.add(input);
                return "added: " + input;
        }
    }

    private static PrintStream getWriter(OutputStream out) {
        return new PrintStream(out);
    }

    private static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }
}
