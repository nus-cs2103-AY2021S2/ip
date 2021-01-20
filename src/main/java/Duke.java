import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String input;
        ArrayList<Task> arrayList = new ArrayList<Task>();
        int listNumber = 1;

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {

                input = scanner.nextLine();
            try {
                if (!(input.contains("todo") || input.contains("event") || input.contains("deadline")|| input.contains("list") || input.contains("done") )) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.equals("todo") || input.equals("event") || input.equals("deadline") || input.equals("done") ) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }                                 // can add in more exceptions
            } catch (DukeException e) {
                System.out.println("--------------------------");
                System.out.println(e.getMessage());
                System.out.println("--------------------------");
            }
            if (input.equals("bye")) {
                System.out.println("--------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");

            } else if (input.equals("list")) {
                System.out.println("--------------------------");
                System.out.println("Here are the tasks in your list:");
                for (Task s : arrayList) {
                    System.out.print(s.index + ". " + s);
                    System.out.print("\n");
                }
                System.out.println("--------------------------");

            } else if (input.contains("done") && !(input.equals("done"))) {

                    int x = Integer.parseInt(input.substring(5));
                try {
                    if (x > arrayList.size()) {
                        throw new DukeException("☹ OOPS!!! Sorry item no found :-(");
                    } else {
                        System.out.println("--------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(arrayList.get(x - 1).markAsDone().getStatusIcon() + " " + arrayList.get(x - 1).description);
                        arrayList.set(x - 1, arrayList.get(x - 1).markAsDone());
                        System.out.println("--------------------------");
                    }
                } catch (DukeException e) {
                    System.out.println("--------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------");
                }


            } else if (input.contains("todo") && !(input.equals("todo"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                Task task = new Todo(input.substring(5));
                task.index = listNumber;
                listNumber++;
                arrayList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");

            } else if (input.contains("deadline") && !(input.equals("deadline")))  {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                String[] parts = input.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Deadline(part1.substring(9), part2);
                task.index = listNumber;
                listNumber++;
                arrayList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");

            } else if (input.contains("event") && !(input.equals("event"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                String[] parts = input.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Event(part1.substring(6), part2.substring(3));
                task.index = listNumber;
                listNumber++;
                arrayList.add(task);
                System.out.println(task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");

        }
          /*  else {
                Task task = new Task(input);
                task.index = listNumber;
                listNumber++;
                arrayList.add(task);
                System.out.println("added: "+ input);
                System.out.println("--------------------------");
               }
        */

    } }
}


