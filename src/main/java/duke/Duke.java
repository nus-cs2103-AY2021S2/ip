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
    private static List<Task> tasks;
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
            String[] tokens = input.split(" ");
            switch (tokens[0]) {
            case "list":
                list();
                break;
            case "done":
                done(Integer.parseInt(tokens[1]));
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

    static void add(String taskDescription) throws IOException {
        tasks.add(new Task(taskDescription));
        write("added: " + taskDescription);
    }

    static void list() throws IOException {
        out.write(divider);
        int lineNum = 1;
        for (Task task : tasks) {
            out.write('\t');
            out.write(lineNum++ + ". ");
            out.write('[');
            out.write(task.getStatusIcon());
            out.write("] ");
            out.write(task.getDescription());
            out.newLine();
        }
        out.write(divider);
        out.flush();
    }

    static void done(int selection) throws IOException {
        Task task = tasks.get(selection - 1);
        task.markAsDone();
        write("  [" + task.getStatusIcon() + "] " + task.getDescription());
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
