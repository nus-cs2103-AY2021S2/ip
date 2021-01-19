import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void blah() throws InvalidInputException {
        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void done(Task task) {
        task.completeTask();
        System.out.println("     Nice! I've marked this task as done:\n     " + task);
    }

    public static void addTask(String type, ArrayList<Task> tasks, Scanner sc) throws InvalidDescriptionException {
        String taskDescription = sc.nextLine();
        Task task;
        if (taskDescription.equals("")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        } else if (type.equals("todo")) {
            task = new ToDo(false, taskDescription);
        } else if (!taskDescription.contains("/by") && !taskDescription.contains("/at")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description format " + type + " is wrong.");
        } else {
            int index = type.equals("deadline") ? taskDescription.indexOf("/by") : taskDescription.indexOf("/at");
            String taskName = taskDescription.substring(0, index);
            String dateTime = taskDescription.substring(index + 4, taskDescription.length());

            if (type.equals("deadline")) {
                task = new Deadline(false, taskName, dateTime);
            } else {
                task = new Event(false, taskName, dateTime);
            }
        }
        tasks.add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        ArrayList<Task> userTasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Hello from\n" + logo);
        System.out.println("     What can I do for you?");
        System.out.println("     _______________________________________\n");

        while (sc.hasNext()) {
            userInput = sc.next();
            System.out.println("     _______________________________________");
            if (userInput.equals("list")) {
                System.out.println("     Here are the tasks in your list");
                list(userTasks);
            } else if (userInput.equals("bye")) {
                bye();
                System.out.println("     _______________________________________\n");
                break;
            } else if (userInput.equals("done")) {
                done(userTasks.get(sc.nextInt() - 1));
            } else if (userInput.equals("todo") || userInput.equals("deadline") || userInput.equals("event") ) {
                try{
                    addTask(userInput, userTasks, sc);
                } catch (InvalidDescriptionException ex) {
                    System.out.println("     " + ex.getMessage());
                }
            } else {
                // Could have just printed the exception message. But doing it to satisfy the minimal requirement.
                try {
                    blah();
                } catch (InvalidInputException ex) {
                    System.out.println("     " + ex.getMessage());
                }
            }
            System.out.println("     _______________________________________\n");
        }
        sc.close();
    }
}
