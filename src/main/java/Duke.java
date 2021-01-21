import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.respond(new String[]{ "Hello! I'm a customized Duke", "What can I do for you?" });

        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            String s = in.readLine();
            if (s.equals("bye")) {
                Duke.respond(new String[]{"Bye. Hope to see you again soon!"});
                break;
            } else {
                Duke.respond(new String[]{s});
            }
        }
    }

    private static void respond(String[] lines) {
        String border = "    ____________________________________________________________";
        String indent = "     ";

        System.out.println(border);
        for (String line: lines) {
            System.out.print(indent);
            System.out.println(line);
        }
        System.out.println(border);
        System.out.println();
    }
}
