import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            try {
                String s = sc.nextLine();
                String[] arr = s.split(" ");
                String taskConfirmation = "Got it. I've added this task:\n";
                if (s.equals("list")) {
                    if (list.isEmpty()) {
                        System.out.println("There are no tasks in your list. Hooray!");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    int index = 1;
                    for (Task task : list) {
                        System.out.println(index + "." + task);
                        index++;
                    }
                } else if (arr[0].equals("done")) {
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }
                    try {
                        int index = Integer.parseInt(arr[1]);
                        if (index < 1 || index > list.size()) {
                            throw new DukeException("OOPS!!! The numbered task does not exist.");
                        }
                        list.get(index - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + list.get(index - 1));
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS!!! You did not enter the number corresponding to the task.");
                    }

                } else if (s.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (arr[0].equals("todo")) {
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDo t = new ToDo(s.substring(5));
                    list.add(t);
                    System.out.println(taskConfirmation + t
                            + "\nNow you have " + list.size()
                            + (list.size() < 2 ? " task " : " tasks ") + "in the list.");
                } else if (arr[0].equals("deadline")) {
                    String[] arr2 = s.split(" /by ");
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (arr2.length < 2) {
                        throw new DukeException("OOPS!!! You did not set a deadline using '/by'.");
                    }
                    Deadline d = new Deadline(arr2[0].substring(9), arr2[1]);
                    list.add(d);
                    System.out.println(taskConfirmation + d
                            + "\nNow you have " + list.size()
                            + (list.size() < 2 ? " task " : " tasks ") + "in the list.");
                } else if (arr[0].equals("event")) {
                    String[] arr3 = s.split(" /at ");
                    if (arr.length < 2) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    if (arr3.length < 2) {
                        throw new DukeException("OOPS!!! You did not set the date/time of the event using '/at'.");
                    }
                    Event e = new Event(arr3[0].substring(6), arr3[1]);
                    list.add(e);
                    System.out.println(taskConfirmation + e
                            + "\nNow you have " + list.size()
                            + (list.size() < 2 ? " task " : " tasks ") + "in the list.");
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but that is an invalid command :-(");
                }
            } catch (DukeException e) {
                System.out.println("Duke has encountered an error: " + e.getMessage());
            }
        }
    }
}
