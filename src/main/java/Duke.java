import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String line = "____________________________________________________________";
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            list(sc.nextLine());
        }
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void echo(String msg) {
        System.out.println(line);
        if (msg.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            System.exit(0);
        }
        else {
            System.out.println(msg);
            System.out.println(line);
        }
    }

    public static void list(String msg) {
        System.out.println(line);
        if (msg.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            System.exit(0);
        }
        else if (msg.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (Task t : list) System.out.println(t);
            System.out.println(line);
        }
        else if (msg.matches("done \\d+")) {
            int id = Integer.parseInt(msg.split(" ")[1]) - 1;
            Task t = list.get(id);
            t.markAsDone();
            System.out.println("[" + t.getStatusIcon() + "] " + t.getName());
            System.out.println(line);
        }
        else {
            list.add(new Task(list.size() + 1, msg));
            System.out.println("added: " + msg);
            System.out.println(line);
        }
    }
}
