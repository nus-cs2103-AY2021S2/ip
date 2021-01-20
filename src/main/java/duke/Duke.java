package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String divider = "\t____________________________________________________________\n";
    private static List<String> tasks;
    private static BufferedReader in;
    private static BufferedWriter out;

    /**
     * Entry point for Duke.Duke
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        tasks = new ArrayList<>();
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new BufferedWriter(new OutputStreamWriter(System.out));


        String[] greeting = {
            "Hello! I'm Duke.Duke",
            "What can I do for you?"
        };
        write(greeting);

        String input = in.readLine();

        while (!input.equals("bye")) {
            switch (input) {
            case "list":
                list();
                break;
            default:
                add(input);
            }
            input = in.readLine();
        }

        write("Bye. Hope to see you again soon!");

        in.close();
        out.close();
    }

    static void add(String task) throws IOException {
        tasks.add(task);
        write("added: " + task);
    }

    static void list() throws IOException {
        out.write(divider);
        int lineNum = 1;
        for (String task : tasks) {
            out.write('\t');
            out.write(lineNum++ + ". ");
            out.write(task);
            out.newLine();
        }
        out.write(divider);
        out.flush();
    }

    static void write(String line) throws IOException {
        out.write(divider);
        out.write('\t');
        out.write(line);
        out.newLine();
        out.write(divider);
        out.flush();
    }

    static void write(String[] lines) throws IOException {
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
