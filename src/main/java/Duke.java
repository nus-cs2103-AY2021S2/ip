import java.util.*;

public class Duke {
    static final String INDENT = "        ";
    static final String BORDER = INDENT + "__________________________________________________";

    public static void print(String s) {
        String[] sSplit = s.split("\n");
        System.out.println(BORDER);
        for(String line: sSplit) {
            System.out.println(INDENT + line);
        }
        System.out.println(BORDER + "\n");
    }
    public static void main(String[] args) {
        String logo = "      _       __  __  \n"
                    + "     | |     / _|/ _| \n"
                    + "     | | ___| |_| |_  \n"
                    + " _   | |/ _ \\  _|  _|\n"
                    + "| |__| |  __/ | | |   \n"
                    + " \\____/ \\___|_| |_| \n";
        print("Hello I'm\n" + logo + "\nWhat can I do for you?");

        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String[] msg = sc.nextLine().split("/");
            String[] msg0 = msg[0].split(" ", 2);
            String cmd = msg0[0];
            if(cmd.equals("bye")) {
                print("Bye. Hope to see you again!");
                break;
            } else if(cmd.equals("list")) {
                String toPrint = "";
                for (int i = 0; i < tasks.size(); i++) {
                    toPrint += (i + 1) + "." + tasks.get(i) + "\n";
                }
                print(toPrint);
            } else if(cmd.equals("done")) {
                int taskIndex = Integer.parseInt(msg0[1]) - 1;
                tasks.get(taskIndex).setDone();
                print("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
            } else if(cmd.equals("todo")) {
                Task curr = new ToDo(msg0[1]);
                tasks.add(curr);
                print("Got it. I've added this task:\n" + curr + "\nNow you have " + tasks.size() + " tasks in the list.");
            } else if(cmd.equals("deadline")) {
                String time = msg[1].split(" ", 2)[1];
                Task curr = new Deadline(msg0[1], time);
                tasks.add(curr);
                print("Got it. I've added this task:\n" + curr + "\nNow you have " + tasks.size() + " tasks in the list.");
            } else if(cmd.equals("event")) {
                String time = msg[1].split(" ", 2)[1];
                Task curr = new Event(msg0[1], time);
                tasks.add(curr);
                print("Got it. I've added this task:\n" + curr + "\nNow you have " + tasks.size() + " tasks in the list.");

            }
        }
    }
}
