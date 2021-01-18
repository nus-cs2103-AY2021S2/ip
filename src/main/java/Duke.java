import java.util.*;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);
        run(sc.nextLine());
        sc.close();
    }

    public static void run(String cmd) {
        System.out.println("    ____________________________________________________________");
        if(cmd.equals("bye")) {
            System.out.println("     Bye. Hope to see you again soon!" + "\n"
                    + "    ____________________________________________________________\n");
        } else if (cmd.equals("list")) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                System.out.println("     " + (i + 1) + "."
                        + curr.getStatusIcon() + curr.getDescription());
            }
            System.out.println("    ____________________________________________________________\n");
            run(sc.nextLine());
        } else if (cmd.startsWith("done")) {
            int index = Integer.parseInt(cmd.substring(5)) - 1;
            tasks.get(index).markAsDone();
            Task curr = tasks.get(index);
            System.out.println("     Nice! I've marked this task as done:\n"
                    + "       " + curr.getStatusIcon() + curr.getDescription() + "\n"
                    + "    ____________________________________________________________\n");
            run(sc.nextLine());
        } else {
            System.out.println("     added: " + cmd + "\n"
                    + "    ____________________________________________________________\n");
            tasks.add(new Task(cmd));
            run(sc.nextLine());
        }
    }
}
