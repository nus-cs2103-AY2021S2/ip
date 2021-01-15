import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "------------------------------------------";
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        //introduction
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        //start reading data from user
        Scanner sc = new Scanner(System.in);
        boolean flagger = true;
        ArrayList<Task> list = new ArrayList<Task>();


        while (flagger) {
            String input = sc.nextLine();
            String action = input.split(" ")[0];
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                flagger = false;

            } else if (input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < list.size() + 1; i++) {
                        System.out.println(i + "." + list.get(i - 1));
                }
                System.out.println(line);

            } else if (action.equals("done")) {
                System.out.println(line);
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                System.out.println("Nice! I've marked this task as done:");
                list.get(index).setDone();
                System.out.println(list.get(index));
                System.out.println(line);


            } else if (action.equals("todo")) {
                String[] temp = input.split(" ", 2);
                if (temp.length == 1) {
                    System.out.println(line);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    Task t = new Todo(temp[1]);
                    list.add(t);
                    System.out.println(t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                }

            } else if (action.equals("deadline")) {
                String[] temp = input.split(" ", 2);
                if (temp.length == 1) {
                    System.out.println(line);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(line);
                } else {
                    String data = temp[1];
                    String description = data.split(" /by ", 2)[0];
                    String by = data.split(" /by ", 2)[1];

                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    Task t = new Deadline(description, by);
                    list.add(t);
                    System.out.println(t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                }
            } else if (action.equals("event")) {
                String[] temp = input.split(" ", 2);

                if (temp.length == 1) {
                    System.out.println(line);
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.println(line);
                } else {
                    String data = temp[1];
                    String description = data.split(" /at ", 2)[0];
                    String at = data.split(" /at ", 2)[1];

                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    Task t = new Event(description, at);
                    list.add(t);
                    System.out.println(t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println(line);
                }
            } else {
                System.out.println(line);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }
    }
}
