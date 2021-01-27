
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import exception.DukeException;
import exception.DukeInvalidInputException;

public class Duke {
    protected static Storage storage = new Storage();
    protected static TaskList taskList = new TaskList(storage);

    /**
     * Entry point for program
     * @param args command line arguments
     * @throws IOException When file cannot be read.
     */
    public static void main(String[] args) throws IOException {

        UI.greet();

        storage.tasks = new ArrayList<>(100);
        storage.inputs = new ArrayList<>(100);
        storage.loadHistory();

        chatLoop(System.in, System.out);

    }

    protected static void chatLoop(InputStream in, OutputStream out) throws IOException {

        BufferedReader reader = getReader(in);
        PrintStream writer = getWriter(out);
        String line = reader.readLine();
        while (line != null) {
            try {
                writer.println(parseInput(line));
                storage.inputs.add(line);
                storage.saveHistory();
            } catch (DukeException e) {
                handleException(e, writer);
            }
            if (line.equals("bye")) {
                break;
            }
            line = reader.readLine();
        }
    }

    private static void handleException(DukeException e, PrintStream writer) {
        writer.println(e.getMessage());
    }

    protected static String parseInput(String input) throws DukeException {
        HashMap<String,String> tokenizedInput = Parser.ParseInput(input); 
        switch (tokenizedInput.get("command")) {
        case "bye":
            return "Bye. Hope to see you again soon!";
        case "list":
            return taskList.executeList();
        case "done":
            return taskList.executeDone(tokenizedInput);
        case "delete":
            return taskList.executeDelete(tokenizedInput);
        case "todo":
            return taskList.executeTodo(tokenizedInput);
        case "deadline":
            return taskList.executeDeadline(tokenizedInput);
        case "event":
            return taskList.executeEvent(tokenizedInput);
        default:
            throw new DukeInvalidInputException(input);
        }
    }

    private static PrintStream getWriter(OutputStream out) {
        return new PrintStream(out);
    }

    private static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }
}
