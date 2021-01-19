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
                if (tasks.size() == 0) {
                    print("No tasks right now");
                } else {
                    String toPrint = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        toPrint += (i + 1) + "." + tasks.get(i) + "\n";
                    }
                    print(toPrint);
                }
            } else if(cmd.equals("done")) {
                if(msg0.length != 2) {
                    print((new DukeException("wrong number of arguments for done")).toString());
                    continue;
                }
                try {
                    int taskIndex = Integer.parseInt(msg0[1]) - 1;
                    tasks.get(taskIndex).setDone();
                    print("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
                } catch(IndexOutOfBoundsException e) {
                    print((new DukeException("task number does not exist")).toString());
                } catch(NumberFormatException e) {
                    print((new DukeException("indicate task number to be marked as done")).toString());
                }
            } else if(cmd.equals("todo")) {
                if (msg0.length < 2) {
                    print((new DukeException("please provide a description for todo")).toString());
                    continue;
                }
                Task curr = new ToDo(msg0[1]);
                tasks.add(curr);
                print("Got it. I've added this task:\n" + curr + "\nNow you have " + tasks.size() + " tasks in the list.");
            } else if(cmd.equals("deadline")) {
                if (msg0.length < 2) {
                    print((new DukeException("please provide a description for deadline")).toString());
                    continue;
                }
                try {
                    String time = msg[1].split(" ", 2)[1];
                    Task curr = new Deadline(msg0[1], time);
                    tasks.add(curr);
                    print("Got it. I've added this task:\n" + curr + "\nNow you have " + tasks.size() + " tasks in the list.");
                } catch(ArrayIndexOutOfBoundsException e) {
                    print((new DukeException("please provide a time for deadline")).toString());
                }
            } else if(cmd.equals("event")) {
                if (msg0.length < 2) {
                    print((new DukeException("please provide a description for event")).toString());
                    continue;
                }
                try {
                    String time = msg[1].split(" ", 2)[1];
                    Task curr = new Event(msg0[1], time);
                    tasks.add(curr);
                    print("Got it. I've added this task:\n" + curr + "\nNow you have " + tasks.size() + " tasks in the list.");
                } catch(ArrayIndexOutOfBoundsException e) {
                    print((new DukeException("please provide a time for event")).toString());
                }
            } else {
                print((new DukeException("I can't understand the message")).toString());
            }
        }
    }
}
