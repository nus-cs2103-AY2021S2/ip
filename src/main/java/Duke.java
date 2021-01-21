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
        ArrayList<String> userData = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            String s = in.readLine();
            if (s.equals("bye")) {
                Duke.respond(outtro);
                break;
            } else if (s.equals("list")) {
                Duke.respondList(userData);
            } else {
                ArrayList<String> line = new ArrayList<>();
                line.add("added: " + s);
                Duke.respond(line);
                userData.add(s);
            }
        }
    }

    private static void respondList(ArrayList<String> lines) {
        ArrayList<String> copy = new ArrayList<>(lines);
        for (int i = 0; i < copy.size(); i++) {
            copy.set(i, (i+1) + ". " + copy.get(i));
        }
        Duke.respond(copy);
    }

    private static void respond(ArrayList<String> lines) {
        String border = "    ____________________________________________________________";
        String indent = "     ";

        System.out.println(border);
        for (String line: lines) {
            System.out.print(indent);
            System.out.println(line);
        }
        System.out.println(border);
        System.out.println();
    }
}
