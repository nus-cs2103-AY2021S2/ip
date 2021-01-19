import java.util.*;

public class Duke {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        run(sc.nextLine(), sc);
        sc.close();
    }

    private static void run(String input, Scanner sc) {
        System.out.println("    ____________________________________________________________");
        String[] inputArr = input.split(" ");
        try {
            if (inputArr[0].equals("bye") && inputArr.length == 1) {
                System.out.println("     Bye. Hope to see you again soon!");
            } else if (inputArr[0].equals("list") && inputArr.length == 1) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    System.out.println("     " + (i + 1) + "." + curr.toString());
                }
            } else if ((inputArr[0].equals("done") || inputArr[0].equals("delete")) && inputArr.length < 3) {
                if (inputArr.length == 1) {
                    throw new DukeException("       OOPS!!! The task number cannot be empty.");
                } else if (!isNumeric(inputArr[1])) {
                    throw new DukeException("       OOPS!!! The task number must be numeric.");
                } else {
                    int i = Integer.parseInt(inputArr[1]) - 1;
                    if (i > (tasks.size() - 1) || i < 0) {
                        throw new DukeException("       OOPS!!! The task number is out of range.");
                    } else {
                        if(inputArr[0].equals("done")) {
                            tasks.get(i).markAsDone();
                            Task curr = tasks.get(i);
                            System.out.println("     Nice! I've marked this task as done:\n"
                                    + "       " + curr.toString());
                        } else if (inputArr[0].equals("delete")) {
                            System.out.println("     Noted. I've removed this task:\n"
                                    + "       " + tasks.remove(i).toString() + "\n"
                                    + "     Now you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                }
            } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                switch (inputArr[0]) {
                    case "todo":
                        if (inputArr.length == 1) {
                            throw new DukeException("       OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            tasks.add(new Todo(input.substring(5)));
                        }
                        break;
                    case "deadline":
                        if (inputArr.length == 1) {
                            throw new DukeException("       OOPS!!! The description and due date of a deadline cannot be empty.");
                        } else {
                            String[] details = input.substring(9).split(" /by ");
                            if (details.length == 1) {
                                throw new DukeException("       OOPS!!! The due date of a deadline cannot be empty.");
                            } else {
                                tasks.add(new Deadline(details[0], details[1]));
                            }
                        }
                        break;
                    case "event":
                        if (inputArr.length == 1) {
                            throw new DukeException("       OOPS!!! The description and time frame of an event cannot be empty.");
                        } else {
                            String[] details = input.substring(6).split(" /at ");
                            if (details.length == 1) {
                                throw new DukeException("       OOPS!!! The time frame of an event cannot be empty.");
                            } else {
                                tasks.add(new Event(details[0], details[1]));
                            }
                        }
                        break;
                }
                System.out.println("     Got it. I've added this task:\n"
                        + "       " + tasks.get(tasks.size() - 1).toString() + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new DukeException("       OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("    ____________________________________________________________\n");
            if (!input.equals("bye")) {
                run(sc.nextLine(), sc);
            }
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
}