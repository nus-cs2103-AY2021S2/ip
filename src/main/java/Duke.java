import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "____________________________________________________________\n";
        System.out.println(divider + logo + "\nHello! I'm Duke\nWhat can I do for you?\n" + divider);

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] check = input.split(" ");
            if(check[0].equals("bye")) {
                System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                break;
            } else if(check[0].equals("list")) {
                System.out.println(divider + "Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println("  " + i + ". " + list.get(i-1));
                }
                System.out.println(divider);
            } else if(check[0].equals("done")) {
                list.get(Integer.parseInt(check[1])-1).markAsDone();
                System.out.println(divider + "Nice! I've marked this task as done:\n  " + list.get(Integer.parseInt(check[1])-1) + "\n" + divider);
            } else if(check[0].equals("todo")) {
                Todo curr = new Todo(input.substring(5,input.length()));
                list.add(curr);
                System.out.println(divider + "Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
            } else if(check[0].equals("deadline")) {
                String[] temp = input.substring(9, input.length()).split(" /by ");
                Deadline curr = new Deadline(temp[0], temp[1]);
                list.add(curr);
                System.out.println(divider + "Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
            } else if(check[0].equals("event")) {
                String[] temp = input.substring(6, input.length()).split(" /at ");
                Event curr = new Event(temp[0], temp[1]);
                list.add(curr);
                System.out.println(divider + "Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
            } else {
                list.add(new Task(input));
                System.out.println(divider + "Added: " + input + "\n" + divider);
            }
        }
    }
}
