package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
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
            try {
                String[] tokens = input.split(" ");
                switch (tokens[0]) {
                case "list":
                    list();
                    break;
                case "done":
                    done(input, tokens);
                    break;
                case "todo":
                    addTodo(input, tokens);
                    break;
                case "deadline":
                    addDeadline(input, tokens);
                    break;
                case "event":
                    addEvent(input, tokens);
                    break;
                case "delete":
                    delete(input, tokens);
                    break;
                default:
                    throw new UnsupportedOperationException();
                }
            } catch (DukeException de) {
                write(de.toString());
            } catch (UnsupportedOperationException noe) {
                write("Unknown command.");
            }

            input = in.readLine();
        }


        write("Bye. Hope to see you again soon!");

        in.close();
        out.close();
    }

    static void addTodo(String input, String[] tokens) throws IOException {
        if (tokens.length < 2) {
            throw new DukeException("Todo requires a description.");
        }
        Todo todo = new Todo(input.substring(5));
        tasks.add(todo);
        writeAddTask(todo);
    }

    static void addDeadline(String input, String[] tokens) throws IOException {
        int bySwitchIndex = input.indexOf("/by");
        if (bySwitchIndex == -1 || bySwitchIndex + 4 > input.length()) {
            throw new DukeException("Deadline requires '/by' to be specified.");
        }
        String description = input.substring(9, bySwitchIndex);
        if (description.trim().equals("")) {
            throw new DukeException("Deadline requires a description.");
        }
        String byStr = input.substring(bySwitchIndex + 4);
        if (byStr.trim().equals("")) {
            throw new DukeException("Deadline requires '/by' to be specified.");
        }

        LocalDate by;
        try {
            by = LocalDate.parse(byStr);
        } catch (DateTimeException dte) {
            throw new DukeException("Deadline /by needs to be in a valid format (e.g. yyyy-MM-dd)");
        }

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        writeAddTask(deadline);
    }

    static void addEvent(String input, String[] tokens) throws IOException {
        int atSwitchIndex = input.indexOf("/at");
        if (atSwitchIndex == -1 || atSwitchIndex + 4 > input.length()) {
            throw new DukeException("Event requires '/at' to be specified.");
        }
        String description = input.substring(6, atSwitchIndex);
        if (description.trim().equals("")) {
            throw new DukeException("Event requires a description.");
        }
        String atStr = input.substring(atSwitchIndex + 4);
        if (atStr.trim().equals("")) {
            throw new DukeException("Event requires '/at' to be specified.");
        }

        LocalDate at;
        try {
            at = LocalDate.parse(atStr);
        } catch (DateTimeException dte) {
            throw new DukeException("Event /at needs to be in a valid format (e.g. yyyy-MM-dd)");
        }

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

    static void done(String input, String[] tokens) throws IOException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify a number");
        }

        int selection;
        try {
            selection = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Invalid number");
        }

        if (selection < 1 || selection > tasks.size()) {
            throw new DukeException("Invalid number");
        }
        Task task = tasks.get(selection - 1);
        task.markAsDone();
        write("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    static void delete(String input, String[] tokens) throws IOException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify a number");
        }

        int selection;
        try {
            selection = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Invalid number");
        }

        if (selection < 1 || selection > tasks.size()) {
            throw new DukeException("Invalid number");
        }
        Task task = tasks.get(selection - 1);
        tasks.remove(selection - 1);
        writeDeleteTask(task);
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

    static void writeDeleteTask(Task task) throws IOException {
        write(new String[]{
            "Noted. I've removed this task:",
            "  " + task,
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }
}
