import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Print introduction
        ArrayList<String> intro = new ArrayList<>();
        intro.add("Hello! I'm a customized Duke");
        intro.add("What can I do for you?");
        Duke.respond(intro);

        ArrayList<String> outtro = new ArrayList<>();
        outtro.add("Bye. Hope to see you again soon!");

        // REPL
        ArrayList<Task> userData = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            String s = in.readLine();
            if (s.equals("bye")) {
                Duke.respond(outtro);
                break;
            } else if (s.equals("list")) {
                Duke.respondList(userData);
            } else if (s.startsWith("done")) {
                int idx = Integer.parseInt(s.substring(5)) - 1;
                Task t = userData.get(idx);
                t.setDone();
                Duke.respondDone(t);
            } else {
                Duke.respond("added: " + s);
                userData.add(new Task(s));
            }
        }
    }

    private static void respondDone(Task t) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Nice! I've marked this task as done:");
        lines.add("  " + t.getDescriptionCheckbox());
        Duke.respond(lines);
    }

    private static void respondList(ArrayList<Task> tasklist) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasklist.size(); i++) {
            Task t = tasklist.get(i);
            lines.add((i+1) + "." + t.getDescriptionCheckbox());
        }
        Duke.respond("Here are the tasks in your list:", lines);
    }

    private static void respond(String comment, ArrayList<String> lines) {
        String border = "    ____________________________________________________________";
        String indent = "     ";

        System.out.println(border);
        if (!comment.isEmpty()) {
            System.out.print(indent);
            System.out.println(comment);
        }
        for (String line: lines) {
            System.out.print(indent);
            System.out.println(line);
        }
        System.out.println(border);
        System.out.println();
    }

    private static void respond(ArrayList<String> lines) {
        Duke.respond("", lines);
    }

    private static void respond(String line) {
        Duke.respond(line, new ArrayList<>());
    }
}
