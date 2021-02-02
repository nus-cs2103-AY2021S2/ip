import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * main method to run the program
     *
     * @param args command line arguments taken in
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+ "What can I do for you?");
        ArrayList<Task> items = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            try {
                errorHandling(input);
                if (input.equals("bye")) {
                    bye();
                    break;
                } else if (input.contains("done")) {
                    System.out.println("Nice! I've marked this task as done: ");

                    int taskNum = Integer.parseInt(input.substring(5));
                    String name = items.get(taskNum - 1).getDescription();

                    Task type = items.get(taskNum - 1);
                    items.remove(taskNum - 1);
                    if (type instanceof Todo) {
                        Todo markDone = new Todo(name, true);
                        items.add(taskNum - 1, markDone);
                        System.out.println(markDone);
                    } else if (type instanceof Deadline) {
                        Deadline markDone = new Deadline(name, true);
                        items.add(taskNum - 1, markDone);
                        System.out.println(markDone);
                    } else {
                        Event markDone = new Event(name, true);
                        items.add(taskNum - 1, markDone);
                        System.out.println(markDone);
                    }

                } else if (input.equals("list")) {
                    int n = 1;
                    System.out.println("Here are the tasks in your list:");
                    for (Task item : items) {
                        System.out.println(n + ". " + item);
                        n++;
                    }

                } else if (input.length() >= 8 && input.substring(0,8).equals("deadline")) {
                    items.add(new Deadline(input.substring(8)));
                } else if (input.length() >= 4 && input.substring(0,4).equals("todo")) {
                    items.add(new Todo(input.substring(4)));
                } else if (input.length() >= 5 && input.substring(0,5).equals("event")) {
                    items.add(new Event(input.substring(5)));
                } else if (input.length() >= 6 && input.substring(0,6).equals("delete")) {
                    int taskToDelete = Integer.parseInt(input.substring(7));
                    Task toRemove = items.get(taskToDelete - 1);
                    items.remove(taskToDelete - 1);
                    System.out.println("Noted. I've removed this task:\n  "
                                       + toRemove + "\nNow you have " + items.size() + " tasks in the list.");

                } else {
                    items.add(new Task(input));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * prints Goodbye message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void errorHandling(String input) throws DukeException {
        if (input.length() == 4 && input.equals("todo")) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.length() == 8 && input.equals("deadline")) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.length() == 5 && input.equals("event")) {
            throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
        } else if (input.length() >= 4 && input.substring(0,4).equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (input.isBlank()) {
            throw new DukeException(" ☹ OOPS!!! A Task cannot be empty.");
        } else {}
    }
}
