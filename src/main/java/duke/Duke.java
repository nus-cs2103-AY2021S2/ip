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
            "Hello! I'm Duke",
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
            case "todo":
                addTodo(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            case "event":
                addEvent(input);
                break;
            default:
                // handle in level 5
            }
            input = in.readLine();
        }

        write("Bye. Hope to see you again soon!");

        in.close();
        out.close();
    }

    static void addTodo(String input) throws IOException {
        Todo todo = new Todo(input.substring(5));
        tasks.add(todo);
        writeAddTask(todo);
    }

    static void addDeadline(String input) throws IOException {
        int bySwitchIndex = input.indexOf("/by");
        String description = input.substring(9, bySwitchIndex);
        String by = input.substring(bySwitchIndex + 4);
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        writeAddTask(deadline);
    }

    static void addEvent(String input) throws IOException {
        int atSwitchIndex = input.indexOf("/at");
        String description = input.substring(6, atSwitchIndex);
        String at = input.substring(atSwitchIndex + 4);
        Event event = new Event(description, at);
        tasks.add(event);
        writeAddTask(event);
    }

    static void list() throws IOException {
        out.write(divider);
        int lineNum = 1;
        for (Task task : tasks) {
            out.write('\t');
            out.write(lineNum++ + ".");
            out.write(task.toString());
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

    static void writeAddTask(Task task) throws IOException {
        write(new String[]{
            "Got it. I've added this task:",
            "  " + task,
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }
}
