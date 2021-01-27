
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import exception.DukeException;
import exception.DukeInvalidArgumentsException;
import exception.DukeInvalidInputException;

public class Duke {
    protected static Storage storage = new Storage();

    protected static final String sessionFile = "./saved_session";
    private static final String greetingMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello from\n" + logo);

        System.out.println(greetingMessage);

        storage.tasks = new ArrayList<>(100);
        storage.inputs = new ArrayList<>(100);
        storage.loadHistory();

        chatLoop(System.in, System.out);

    }

    public static void chatLoop(InputStream in, OutputStream out) throws IOException {

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

    public static String parseInput(String input) throws DukeException {
        HashMap<String,String> tokenizedInput = Parser.ParseInput(input); 
        switch (tokenizedInput.get("command")) {
        case "bye":
            return "Bye. Hope to see you again soon!";
        case "list":
            return executeList();
        case "done":
            return executeDone(tokenizedInput);
        case "delete":
            return executeDelete(tokenizedInput);
        case "todo":
            return executeTodo(tokenizedInput);
        case "deadline":
            return executeDeadline(tokenizedInput);
        case "event":
            return executeEvent(tokenizedInput);
        default:
            throw new DukeInvalidInputException(input);
        }
    }

    private static String executeEvent(HashMap<String,String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("event", "The description of an event cannot be empty");
        }
        if (!input.containsKey("at")) {
            throw new DukeInvalidArgumentsException("event", "The date for an event cannot be empty");
        }
        return addTaskAndReturnMessage(new EventTask(input.get("info"), input.get("at")));
    }

    private static String executeDeadline(HashMap<String,String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("deadline", "The description of a deadline cannot be empty");
        }
        if (!input.containsKey("by")) {
            throw new DukeInvalidArgumentsException("deadline", "The date for a deadline cannot be empty");
        }
        return addTaskAndReturnMessage(new DeadlineTask(input.get("info"), input.get("by")));
    }

    private static String executeTodo(HashMap<String,String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("todo", "The description of a todo cannot be empty");
        }
        return addTaskAndReturnMessage(new TodoTask(input.get("info")));
    }

    private static String addTaskAndReturnMessage(Task task) {
        storage.tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                storage.tasks.size());
    }

    private static String executeList() {
        String output = "";
        for (int i = 0; i < storage.tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, storage.tasks.get(i));
        }
        return output.substring(0, output.length() - 1);
    }

    private static String executeDelete(HashMap<String,String> tokenizedInput) {
        // TODO: Add Exception for out of range deletion
        Task t = storage.tasks.remove(Integer.parseInt(tokenizedInput.get("info")) - 1);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d storage.tasks in the list.", t.toString(),
                storage.tasks.size());
    }

    private static String executeDone(HashMap<String,String> tokenizedInput) {
        // TODO: Add Exception for out of range done
        Task t = storage.tasks.get(Integer.parseInt(tokenizedInput.get("info")) - 1);
        t.setTaskAsDone();
        return "Nice! I've marked this task as done:\n  " + t.toString();
    }

    private static PrintStream getWriter(OutputStream out) {
        return new PrintStream(out);
    }

    private static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }
}
