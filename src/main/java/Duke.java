import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String line = "____________________________________________________________";
    public static ArrayList<String> list = new ArrayList<String>();

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
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
            System.out.println(line);
        }
        else {
            list.add(msg);
            System.out.println("added: " + msg);
            System.out.println(line);
        }
    }
}
