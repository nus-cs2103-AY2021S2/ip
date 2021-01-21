import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> lst = new ArrayList<>();
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < lst.size(); i++) {
                    int count = i + 1;
                    Task a = lst.get(i);
                    System.out.println(count + ". " + a);
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (input.equals("done")) {
                int tag = sc.nextInt() - 1;
                Task d = lst.get(tag);
                d.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(d);
            } else if (input.equals("delete")) {
                int tag = sc.nextInt() - 1;
                int len = lst.size() - 1;
                System.out.println("Noted. I've removed this task:");
                System.out.println("\t" + lst.get(tag));
                System.out.println("Now you have " + len + " tasks in the list.");
                lst.remove(tag);
            } else if (input.equals("todo")) {
                String task = sc.nextLine();
                try {
                    Todo td = Todo.makeTodo(task);
                    lst.add(td);
                    int len = lst.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + td);
                    System.out.println("Now you have " + len + " tasks in the list.");
                } catch (DukeException ex){
                    System.err.print("☹ OOPS!!!The description of a todo cannot be empty.\n");
                }
            } else if (input.equals("deadline")) {
                String line = sc.nextLine();
                try {
                    Deadline dl = Deadline.makeDeadline(line);
                    lst.add(dl);
                    int len = lst.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + dl);
                    System.out.println("Now you have " + len + " tasks in the list.");
                } catch (DukeException ex){
                    System.err.print("☹ OOPS!!!The description of a deadline cannot be empty.\n");
                }
            } else if (input.equals("event")) {
                String line = sc.nextLine();
                try {
                    Event event = Event.makeEvent(line);
                    lst.add(event);
                    int len = lst.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event);
                    System.out.println("Now you have " + len + " tasks in the list.");
                } catch (DukeException ex){
                    System.err.print("☹ OOPS!!!The description of an event cannot be empty.\n");
                }
            } else {
                sc.nextLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}