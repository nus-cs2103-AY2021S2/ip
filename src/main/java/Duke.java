import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        int size = 0;

        while (true) {

            size = tasks.size();

            try {
                if (string.equals("bye")) {

                    System.out.println("Bye. Hope to see you again soon");
                    break;

                } else if (string.equals("list")) {

                    if (size == 0) {
                        throw new DukeException("☹ OOPS!!! There is currently no tasks for you.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < size; i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }

                } else if (string.equals("todo") || string.equals("deadline")|| string.equals("event")
                        || string.equals("done") || string.equals("delete")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + string + " cannot be empty.");
                } else {

                    String action = "", info = "";
                    if (string.contains(" ")) {
                        String[] str = string.split(" ", 2);
                        action = str[0];
                        info = str[1];
                    }

                    if (action.equals("done") || action.equals("delete")) {

                        int num = 0;

                        try {
                            num = Integer.parseInt(info);
                        } catch (NumberFormatException e) {
                            throw new DukeException("☹ OOPS!!! There is no such task number.");
                        }

                        if (num > size || num == 0) {
                            throw new DukeException("☹ OOPS!!! There is no such task number.");
                        } else {
                            goneTask(action, num - 1, tasks);
                        }

                    } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
                        addTask(action, info, tasks);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                }

            } catch (DukeException e) {
                System.out.println(e);
            }
            string = scan.nextLine();

        }

    }

    /**
     * Method to delete or done the task according to the task number.
     */
    public static void goneTask(String action, int num, ArrayList<Task> tasks) {

        int size = tasks.size();

        if (action.equals("done")) {
            tasks.get(num).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  "
                    + tasks.get(num));
        } else {
            Task t = tasks.get(num);
            tasks.remove(num);
            System.out.println("Noted. I've removed this task:\n  "
                    + t + "\nNow you have "
                    + (size - 1) + " tasks in the list.");
        }

    }

    /**
     * Method to add the requested task to the list of tasks.
     */
    public static void addTask(String action, String info, ArrayList<Task> tasks) throws DukeException {

        String task = "", time = "";
        int size = tasks.size();

        if (info.contains(" /")) {
            String[] str = info.split(" /", 2);
            task = str[0];
            if (str[1].contains(" ")) {
                str = str[1].split(" ", 2);
                time = str[1];
            }
        } else {
            task = info;
        }

        if (task.equals("")) {
            throw new DukeException("☹ OOPS!!! There is no task given");
        } else {
            if (action.equals("todo")) {
                tasks.add(new Todo(task));
            } else if (action.equals("deadline")) {
                tasks.add(new Deadline(task, time));
            } else if (action.equals("event")) {
                tasks.add(new Event(task, time));
            }
        }

        System.out.println("Got it. I've added this task:\n"
                    + "  " + tasks.get(size).toString()
                    + "\nNow you have " + (size + 1) + " tasks in the list.");

    }

}
