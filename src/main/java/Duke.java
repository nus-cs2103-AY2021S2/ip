import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        int k, i = 0;

        while (true) {

            try {
                if (string.equals("bye")) {

                    System.out.println("Bye. Hope to see you again soon");
                    break;

                } else if (string.equals("list")) {

                    if (i == 0) {
                        throw new DukeException("☹ OOPS!!! There is currently no tasks for you.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int j = 0; j < i; j++) {
                            k = j + 1;
                            System.out.println(k + "." + tasks.get(j));
                        }
                    }

                } else {

                    String front = "", back = "";
                    if (string.contains(" ")) {
                        String[] str = string.split(" ", 2);
                        front = str[0];
                        back = str[1];
                    }

                    if (back.equals("")) {
                        if (string.equals("todo") || string.equals("deadline")
                                || string.equals("event") || string.equals("done")) {
                            throw new DukeException("☹ OOPS!!! The description of a " + string + " cannot be empty.");
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }

                    if (front.equals("done") || front.equals("delete")) {
                        k = Integer.parseInt(back);
                        if (k > i || k == 0) {
                            throw new DukeException("☹ OOPS!!! There is no task number " + k + ".");
                        } else if (front.equals("done")) {
                            tasks.get(k - 1).markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n  "
                                    + tasks.get(k - 1));
                        } else {
                            Task t = tasks.get(k - 1);
                            tasks.remove(k - 1);
                            System.out.println("Noted. I've removed this task:\n  "
                                    + t + "\nNow you have "
                                    + --i + " tasks in the list.");
                        }
                    } else {

                        String task = "", time = "";
                        if (back.contains("/")) {
                            String[] str = back.split("/", 2);
                            task = str[0];
                            time = str[1];
                        } else {
                            task = back;
                        }

                        if (front.equals("todo")) {
                            tasks.add(new Todo(task));
                            task = "";
                        } else if (front.equals("deadline")) {
                            tasks.add(new Deadline(task, time));
                            task = "";
                        } else if (front.equals("event")) {
                            tasks.add(new Event(task, time));
                            task = "";
                        }

                        if (task.equals("")) {
                            System.out.println("Got it. I've added this task:\n"
                                    + "  " + tasks.get(i).toString()
                                    + "\nNow you have " + ++i + " tasks in the list.");
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }

                }

            } catch (DukeException e) {
                System.out.println(e);
            }
            string = scan.nextLine();

        }

    }

}
