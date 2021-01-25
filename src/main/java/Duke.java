import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.DukeCommand;
import exceptions.*;

public class Duke {

    static FileLoader loader;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Print introduction
        Duke.respond(new String[]{
                "Hello! I'm a customized Duke",
                "What can I do for you?",
        });

        // REPL
        // TODO: Exception for when file cannot be written to
        try {
            loader = new FileLoader("./data/tasks.txt");
        } catch (IOException e) {
            System.out.println("TODO: File cannot be written to, terminate here");
            System.exit(1);
        }
        TaskList tasks = loader.read();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        boolean stopProgram = false;
        while (!stopProgram) {
            try {
                String s = in.readLine();
                DukeCommand cmd = DukeCommand.getCommand(s);
                String arg;
                switch (cmd) {
                case BYE:
                    Duke.respond("Bye. Hope to see you again soon!");
                    stopProgram = true;
                    break;
                case LIST:
                    Duke.respondList(tasks);
                    break;
                case DONE:
                    arg = DukeCommand.getArgument(s);
                    Duke.processDone(arg, tasks);
                    break;
                case DELETE:
                    arg = DukeCommand.getArgument(s);
                    Duke.processDelete(arg, tasks);
                        break;
                case TODO:
                    arg = DukeCommand.getArgument(s);
                    Duke.processTodo(arg, tasks);
                    break;
                case DEADLINE:
                    arg = DukeCommand.getArgument(s);
                    Duke.processDeadline(arg, tasks);
                    break;
                case EVENT:
                    arg = DukeCommand.getArgument(s);
                    Duke.processEvent(arg, tasks);
                    break;
                }
            } catch (DukeException | IOException e) {
                Duke.respond(e.toString());
            }
        }
    }

    // TODO: Shift TaskList size checking to TaskList
    private static void processDone(String s, TaskList tasks) throws DukeExceptionIllegalArgument {
        int idx;
        try {
            idx = Integer.parseInt(s) - 1;
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The integer cannot be parsed.");
        }
        if (tasks.size() <= idx || idx < 0) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The task number must be a valid task.");
        }
        tasks.setDone(idx);
        loader.write(tasks);
        Duke.respondDone(tasks.getTask(idx));
    }

    private static void processDelete(String s, TaskList tasks) throws DukeExceptionIllegalArgument {
        int idx;
        try {
            idx = Integer.parseInt(s) - 1;
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The integer cannot be parsed.");
        }
        if (tasks.size() <= idx || idx < 0) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The task number must be a valid task.");
        }
        Task t = tasks.getTask(idx);
        tasks.deleteTask(idx);
        loader.write(tasks);
        Duke.respondDelete(t, tasks);
    }

    private static void processTodo(String s, TaskList tasks) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task t = tasks.addTodo(s);
        loader.write(tasks);
        Duke.respondAdd(t, tasks);
    }

    private static void processDeadline(String s, TaskList tasks) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] tokens = s.split(" /by ");
        if (tokens[0].equals("")) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (tokens.length == 1 || (tokens.length == 2 && tokens[1].equals(""))) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! A deadline must have a due date.");
        }

        Task t;
        try {
            t = tasks.addDeadline(tokens[0], tokens[1]);
        } catch (DukeExceptionIllegalDate e) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! A deadline should be in format 'YYYY-MM-DD hh:mm'.");
        }
        loader.write(tasks);
        Duke.respondAdd(t, tasks);
    }

    private static void processEvent(String s, TaskList tasks) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] tokens = s.split(" /at ");
        if (tokens[0].equals("")) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (tokens.length == 1 || (tokens.length == 2 && tokens[1].equals(""))) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! An event must have a time.");
        }

        Task t;
        try {
            t = tasks.addEvent(tokens[0], tokens[1]);
        } catch (DukeExceptionIllegalDate e) {
            throw new DukeExceptionIllegalArgument("☹ OOPS!!! A deadline should be in format 'YYYY-MM-DD hh:mm'.");
        }
        loader.write(tasks);
        Duke.respondAdd(t, tasks);
    }

    private static void respondDone(Task t) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Nice! I've marked this task as done:");
        lines.add("  " + t);
        Duke.respond(lines);
    }

    private static void respondList(TaskList tasks) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add((i+1) + "." + tasks.getTask(i));
        }
        Duke.respond("Here are the tasks in your list:", lines);
    }

    private static void respondDelete(Task t, TaskList tasks) {
        Duke.respond(
                "Noted. I've removed this task:",
                List.of("  " + t),
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }

    private static void respondAdd(Task t, TaskList tasks) {
        Duke.respond(
                "Got it. I've added this task:",
                List.of("  " + t),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    // General method
    private static void respond(String pre, List<String> lines, String post) {
        String border = "    ____________________________________________________________";
        String indent = "     ";

        System.out.println(border);
        if (!pre.isEmpty()) {
            System.out.print(indent);
            System.out.println(pre);
        }
        for (String line: lines) {
            System.out.print(indent);
            System.out.println(line);
        }
        if (!post.isEmpty()) {
            System.out.print(indent);
            System.out.println(post);
        }
        System.out.println(border);
        System.out.println();
    }

    private static void respond(String pre, List<String> lines) {
        Duke.respond(pre, lines, "");
    }

    private static void respond(String line) {
        Duke.respond(line, new ArrayList<>());
    }

    private static void respond(String[] lines) {
        Duke.respond("", Arrays.asList(lines));
    }

    private static void respond(ArrayList<String> lines) {
        Duke.respond("", lines);
    }
}
