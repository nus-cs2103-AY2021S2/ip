import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import exceptions.*;

public class Duke {

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

        // REPLu
        ArrayList<Task> userData = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String s = in.readLine();
                if (s.equals("bye")) {
                    Duke.respond("Bye. Hope to see you again soon!");
                    break;

                } else if (s.equals("list")) {
                    Duke.respondList(userData);

                } else if (s.startsWith("done")) {
                    int idx = Integer.parseInt(s.substring(5)) - 1;
                    if (userData.size() <= idx || idx < 0) {
                        throw new DukeExceptionIllegalArgument("☹ OOPS!!! The task number must be a valid task.");
                    }
                    Task t = userData.get(idx);
                    t.setDone();
                    Duke.respondDone(t);

                } else {
                    // Task addition
                    Task t; // default empty
                    if (s.startsWith("todo")) {
                        s = s.substring(5);
                        if (s.equals("")) {
                            throw new DukeExceptionIllegalArgument("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        t = new ToDo(s);

                    } else if (s.startsWith("deadline")) {
                        s = s.substring(9);
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
                        t = new Deadline(tokens[0], tokens[1]);

                    } else if (s.startsWith("event")) {
                        s = s.substring(6);
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
                        t = new Deadline(tokens[0], tokens[1]);

                    } else {
                        throw new DukeExceptionIllegalArgument("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    userData.add(t);
                    Duke.respondAdd(t, userData);
                }
            } catch (DukeException e) {
                Duke.respond(e.toString());
            } catch (IOException e) {
                Duke.respond(e.toString());
            }
        }
    }

    private static void respondDone(Task t) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Nice! I've marked this task as done:");
        lines.add("  " + t);
        Duke.respond(lines);
    }

    private static void respondList(ArrayList<Task> tasklist) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasklist.size(); i++) {
            Task t = tasklist.get(i);
            lines.add((i+1) + "." + t);
        }
        Duke.respond("Here are the tasks in your list:", lines);
    }

    private static void respondAdd(Task t, ArrayList<Task> tasklist) {
        Duke.respond(
                "Got it. I've added this task:",
                Arrays.asList(new String[]{"  "+String.valueOf(t)}),
                "Now you have " + tasklist.size() + " tasks in the list.");
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

    private static void respond(String lines[]) {
        Duke.respond("", Arrays.asList(lines));
    }

    private static void respond(ArrayList<String> lines) {
        Duke.respond("", lines);
    }
}
