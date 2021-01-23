import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Chatbot that provides todo-list function for users.
 */
public class Chatbot {
    private final List<Task> todo;

    Chatbot() {
        todo = new ArrayList<>();
    }

    /**
     * Print out all the things in the list.
     *
     * @param todo The list of things to do.
     */
    public static void printTodo(List<Task> todo) throws DukeException {
        if (todo.size() == 0) {
            throw new DukeException("There's currently no any tasks.\n");
        }
        System.out.print(Duke.horizontalLine);
        for (int i = 0; i < todo.size(); i++) {
            System.out.println("\t  " + (i + 1) + "." + todo.get(i).toString());
        }
        System.out.println(Duke.horizontalLine);
    }

    /**
     * Mark the task as done.
     * @param order the order of the task
     * @throws DukeException if there's no such task or the task had been finished
     */
    public void markDone(int order) throws DukeException {
        if (order < 0 || order >= todo.size()) {
            throw new DukeException("There's no task " + (order + 1) + " in the list.\n");
        } else if (todo.get(order).getDone()) {
            throw new DukeException("This task has been finished before.\n");
        }
        todo.get(order).markDone();
        System.out.print(Duke.horizontalLine);
        System.out.println("\t  Nice! I've marked this task as done:");
        System.out.println("\t\t " + todo.get(order).toString());
        System.out.println(Duke.horizontalLine);

    }

    /**
     * Delete the task.
     * @param order the order of the task
     * @throws DukeException if there's no such task
     */
    public void delete(int order) throws DukeException {
        if (order < 0 || order >= todo.size()) {
            throw new DukeException("There's no task " + (order + 1) + " in the list.\n");
        }
        System.out.print(Duke.horizontalLine);
        System.out.println("\t  Noted. I've removed this task:");
        System.out.println("\t\t " + todo.get(order).toString());
        System.out.println(Duke.horizontalLine);
        todo.remove(order);
    }

    /**
     * Add more task.
     * @param newTask Append the newTask to the end of the task list.
     */
    public void addTask(Task newTask) {
        todo.add(newTask);
        System.out.print(Duke.horizontalLine);
        System.out.println("\t  Got it. I've added this task:");
        System.out.println("\t\t" + newTask.toString());
        System.out.println("\t  Now you have " + todo.size() + " tasks in the list.");
        System.out.println(Duke.horizontalLine);
    }

    /**
     * Execute the chat bot.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] taskTimeSplit;
        Task newTask;

        while (!input.toLowerCase().equals("bye")) {
            try {
                String[] taskTypeSplit = input.split(" ");
                if (input.toLowerCase().equals("list")) {
                    printTodo(todo);
                } else if (taskTypeSplit[0].toLowerCase().contains("done")) {
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of done cannot be empty.\n");
                    }
                    int tempOrder = Integer.parseInt(taskTypeSplit[1]);
                    markDone(tempOrder - 1);
                } else if (taskTypeSplit[0].toLowerCase().contains("delete")) {
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of delete cannot be empty.\n");
                    }
                    int tempOrder = Integer.parseInt(taskTypeSplit[1]);
                    delete(tempOrder - 1);
                } else if (taskTypeSplit[0].toLowerCase().equals("todo")) {
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.\n");
                    }
                    newTask = new ToDo(input.substring(5), TaskType.TODO);
                    addTask(newTask);
                } else if (taskTypeSplit[0].toLowerCase().equals("deadline")) {
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of a deadline cannot be empty.\n");
                    }
                    taskTimeSplit = input.split(" /by ");
                    if (taskTimeSplit.length <= 1 || taskTimeSplit[1].isBlank()) {
                        throw new DukeException("The time of a deadline cannot be empty.\n");
                    }
                    newTask = new Deadline(taskTimeSplit[0].substring(9), TaskType.DEADLINE, LocalDate.parse(taskTimeSplit[1]));
                    addTask(newTask);
                } else if (taskTypeSplit[0].toLowerCase().equals("event")) {
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of an event cannot be empty.\n");
                    }
                    taskTimeSplit = input.split(" /at ");
                    if (taskTimeSplit.length <= 1 || taskTimeSplit[1].isBlank()) {
                        throw new DukeException("The time of an event cannot be empty.\n");
                    }
                    newTask = new Event(taskTimeSplit[0].substring(6), TaskType.EVENT, LocalDate.parse(taskTimeSplit[1]));
                    addTask(newTask);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException ex) {
                System.out.println(ex.toString());
            } catch (DateTimeParseException ex) {
                String returnString = "\t  OOPS!!! " + "The format of date is wrong! (yyyy-mm-dd)\n";
                System.out.println(Duke.horizontalLine + returnString + Duke.horizontalLine);
            }
            input = sc.nextLine();
        }

    }

}
