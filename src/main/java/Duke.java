import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static void list(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void blah() throws InvalidInputException {
        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void addTask(String type, ArrayList<Task> tasks, String taskDescription)
            throws InvalidDescriptionException, DateTimeParseException {
        Task task;
        if (taskDescription.equals("")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description of " + type + " cannot be empty.");
        } else if (type.equals("todo")) {
            task = new ToDo(taskDescription);
        } else if ((type.equals("deadline") && !taskDescription.contains("/by"))
                || (type.equals("event")) && !taskDescription.contains("/at")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description format of " + type + " is wrong.");
        } else {
            int index = type.equals("deadline") ? taskDescription.indexOf("/by") : taskDescription.indexOf("/at");
            String taskName = taskDescription.substring(0, index);
            String dateTimeString = taskDescription.substring(index + 4).strip().replace("/", "-");
            LocalDate dateTime = LocalDate.parse(dateTimeString);

            if (type.equals("deadline")) {
                task = new Deadline(taskName, dateTime);
            } else {
                task = new Event(taskName, dateTime);
            }
        }
        tasks.add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    public static void delete(ArrayList<Task> tasks, String taskDescription) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("     " + task);
            System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big " +
                    "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    public static void done(ArrayList<Task> tasks, String taskDescription) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = tasks.get(index);
            task.completeTask();
            System.out.println("     Nice! I've marked this task as done:\n     " + task);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big " +
                    "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String taskDescription;
        ArrayList<Task> userTasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Hello from\n" + logo);
        System.out.println("\n     What can I do for you?");
        System.out.println("     _______________________________________\n");

        while (sc.hasNext()) {
            try {
                userInput = sc.next();
                taskDescription = sc.nextLine().strip();
                System.out.println("     _______________________________________");
                if (userInput.equals("list")) {
                    list(userTasks);
                } else if (userInput.equals("bye")) {
                    bye();
                    break;
                } else if (userInput.equals("done")) {
                    done(userTasks, taskDescription);
                } else if (userInput.equals("todo") || userInput.equals("deadline") || userInput.equals("event")) {
                    addTask(userInput, userTasks, taskDescription);
                } else if (userInput.equals("delete")) {
                    delete(userTasks, taskDescription);
                } else {
                    blah();
                }
            } catch (InvalidDescriptionException | InvalidInputException ex) {
                System.out.println("     " + ex.getMessage());
            } catch (DateTimeParseException ex) {
                System.out.println("Your DateTime format is wrong! The correct format is yyyy-MM-dd");
            } finally {
                System.out.println("     _______________________________________\n");
            }
        }
        sc.close();
    }
}
