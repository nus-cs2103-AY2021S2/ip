
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    public static ArrayList<Task> tasks;

    private static final String greetingMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello from\n" + logo);

        System.out.println(greetingMessage);

        tasks = new ArrayList<>(100);

        chatLoop(System.in, System.out);

    }

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
        String[] tokenizedInput = input.split(" ");
        switch (tokenizedInput[0]) {
            case "bye":
                return "Bye. Hope to see you again soon!";
            case "list":
                return executeList();
            case "done":
                return executeDone(tokenizedInput);
            case "todo":
                return executeTodo(input);
            case "deadline":
                return executeDeadline(input);
            case "event":
                return executeEvent(input);
            default:
                return addTaskAndReturnMessage(new Task(input));
        }
    }

    private static String executeEvent(String input) {
        String[] data = input.substring(6).split("/at");
        return addTaskAndReturnMessage(new EventTask(data[0].trim(), data[1].trim()));
    }

    private static String executeDeadline(String input) {
        String[] data = input.substring(9).split("/by");
        return addTaskAndReturnMessage(new DeadlineTask(data[0].trim(), data[1].trim()));
    }

    private static String executeTodo(String input) {
        String info = input.substring(5);
        return addTaskAndReturnMessage(new TodoTask(info));
    }

    private static String addTaskAndReturnMessage(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                tasks.size());
    }

    private static String executeList() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        return output.substring(0, output.length() - 1);
    }

    private static String executeDone(String[] tokenizedInput) {
        Task t = tasks.get(Integer.parseInt(tokenizedInput[1]) - 1);
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
