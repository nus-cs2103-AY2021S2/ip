package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import data.Task;
import data.TaskList;

public class TextUi {
    private static final String divider = "\t____________________________________________________________\n";

    private BufferedReader in;
    private BufferedWriter out;

    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * TextUi constructor
     * @param in
     * @param out
     */
    public TextUi(InputStream in, OutputStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    /**
     * Writes greeting message to output
     * @throws IOException
     */
    public void writeGreeting() throws IOException {
        String[] greeting = {
            "Hello! I'm Duke",
            "What can I do for you?"
        };
        write(greeting);
    }

    /**
     * Writes the given line with dividers
     * @param line
     * @throws IOException
     */
    public void write(String line) throws IOException {
        out.write(divider);
        out.write('\t');
        out.write(line);
        out.newLine();
        out.write(divider);
        out.flush();
    }

    /**
     * Writes the given lines with dividers
     * @param lines
     * @throws IOException
     */
    public void write(String[] lines) throws IOException {
        out.write(divider);
        for (String line : lines) {
            out.write('\t');
            out.write(line);
            out.newLine();
        }
        out.write(divider);
        out.flush();
    }

    /**
     * Writes the acknowledgement message for adding a task
     * @param task
     * @param tasks
     * @throws IOException
     */
    public void writeAddTask(Task task, TaskList tasks) throws IOException {
        write(new String[]{
            "Got it. I've added this task:",
            "  " + task,
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Writes the acknowledgement message for deleting a task
     * @param task
     * @param tasks
     * @throws IOException
     */
    public void writeDeleteTask(Task task, TaskList tasks) throws IOException {
        write(new String[]{
            "Noted. I've removed this task:",
            "  " + task,
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Writes the acknowledgement message for finishing a task
     * @param task
     * @throws IOException
     */
    public void writeDoneTask(Task task) throws IOException {
        write("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Closes the input and output streams
     * @throws IOException
     */
    public void close() throws IOException {
        in.close();
        out.close();
    }
}
