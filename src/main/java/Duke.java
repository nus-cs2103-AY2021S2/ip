import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Duke {
    private static final String divider = "\t____________________________________________________________\n";

    /**
     * Entry point for Duke
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] greeting = {
                "Hello! I'm Duke",
                "What can I do for you?"
            };
            write(out, greeting);

            String input = in.readLine();

            while (!input.equals("bye")) {
                write(out, input);
                input = in.readLine();
            }

            write(out, "Bye. Hope to see you again soon!");
        }
    }

    static void write(BufferedWriter out, String line) throws IOException {
        out.write(divider);
        out.write('\t');
        out.write(line);
        out.newLine();
        out.write(divider);
        out.flush();
    }

    static void write(BufferedWriter out, String[] lines) throws IOException {
        out.write(divider);
        for (String line : lines) {
            out.write('\t');
            out.write(line);
            out.newLine();
        }
        out.write(divider);
        out.flush();
    }
}
