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
                    String stats = lst.get(i).getStatus();
                    System.out.println(count + ". " + stats + " " + lst.get(i).des);
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (input.equals("done")) {
                int tag = sc.nextInt() - 1;
                Task d = lst.get(tag);
                d.markAsDone();
                String stat = d.getStatus();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(stat + " " + d.des);
            } else {
                String ninput = input + sc.nextLine();
                Task t = new Task(ninput);
                lst.add(t);
                System.out.println("added: " + t.des);
            }
        }
    }
}