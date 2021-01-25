import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
        try {
            String[] inputArr = input.split(" ");
            Command cmd = Command.valueOf(inputArr[0].toUpperCase());
            switch (cmd) {
                case BYE:
                    if (inputArr.length == 1) {
                        System.out.println("     Bye. Hope to see you again soon!");
                    }
                    break;
                case LIST:
                    if (inputArr.length == 1) {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            Task curr = tasks.get(i);
                            System.out.println("     " + (i + 1) + "." + curr.toString());
                        }
                    }
                    break;
                case DONE:
                case DELETE:
                    if (inputArr.length < 3) {
                        if (inputArr.length == 1) {
                            throw new DukeException("       OOPS!!! The task number cannot be empty.");
                        } else if (!isNumeric(inputArr[1])) {
                            throw new DukeException("       OOPS!!! The task number must be numeric.");
                        } else {
                            int i = Integer.parseInt(inputArr[1]) - 1;
                            if (i > (tasks.size() - 1) || i < 0) {
                                throw new DukeException("       OOPS!!! The task number is out of range.");
                            } else {
                                switch (cmd) {
                                    case DONE:
                                        tasks.get(i).markAsDone();
                                        Task curr = tasks.get(i);
                                        System.out.println("     Nice! I've marked this task as done:\n"
                                                + "       " + curr.toString());
                                        break;
                                    case DELETE:
                                        System.out.println("     Noted. I've removed this task:\n"
                                                + "       " + tasks.remove(i).toString() + "\n"
                                                + "     Now you have " + tasks.size() + " tasks in the list.");
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    switch (cmd) {
                        case TODO:
                            if (inputArr.length == 1) {
                                throw new DukeException("       OOPS!!! The description of a todo "
                                        + "cannot be empty.");
                            } else {
                                tasks.add(new Todo(input.substring(5)));
                            }
                            break;
                        case DEADLINE:
                            if (inputArr.length == 1) {
                                throw new DukeException("       OOPS!!! The description and due date "
                                        + "of a deadline cannot be empty.");
                            } else {
                                String[] details = input.substring(9).split(" /by ");
                                if (details.length == 1) {
                                    throw new DukeException("       OOPS!!! The due date of a deadline "
                                            + "cannot be empty.");
                                } else {
                                    tasks.add(new Deadline(details[0], LocalDate.parse(details[1])));
                                }
                            }
                            break;
                        case EVENT:
                            if (inputArr.length == 1) {
                                throw new DukeException("       OOPS!!! The description and time frame "
                                        + "of an event cannot be empty.");
                            } else {
                                String[] details = input.substring(6).split(" /at ");
                                if (details.length == 1) {
                                    throw new DukeException("       OOPS!!! The time frame of an event "
                                            + "cannot be empty.");
                                } else {
                                    tasks.add(new Event(details[0], LocalDate.parse(details[1])));
                                }
                            }
                            break;
                    }
                    System.out.println("     Got it. I've added this task:\n"
                            + "       " + tasks.get(tasks.size() - 1).toString() + "\n"
                            + "     Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    throw new DukeException("       OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("       OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DateTimeParseException ex) {
            System.out.println("       OOPS!!! The date has to be in the format yyyy-mm-dd.");
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