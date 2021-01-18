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
            System.out.println("     Bye. Hope to see you again soon!");
        } else if (cmd.equals("list")) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                System.out.println("     " + (i + 1) + "." + curr.toString());
            }
        } else if (cmd.startsWith("done")) {
            int i = Integer.parseInt(cmd.substring(5)) - 1;
            tasks.get(i).markAsDone();
            Task curr = tasks.get(i);
            System.out.println("     Nice! I've marked this task as done:\n"
                    + "       " + curr.toString());
        } else {
            Task curr;
            if (cmd.startsWith("todo")) {
                curr = new Todo(cmd.substring(5));
            } else if (cmd.startsWith("deadline")) {
                int end = cmd.indexOf('/') - 1;
                curr = new Deadline(cmd.substring(9, end), cmd.substring(end + 5));
            } else {
                int end = cmd.indexOf('/') - 1;
                curr = new Event(cmd.substring(6, end), cmd.substring(end + 5));
            }
            tasks.add(curr);
            System.out.println("     Got it. I've added this task:\n"
                    + "       " + curr.toString() + "\n"
                    + "     Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("    ____________________________________________________________\n");

        if(!cmd.equals("bye")) {
            run(sc.nextLine());
        }
    }
}
