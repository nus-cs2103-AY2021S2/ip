package duke.customClass;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.List;

/**
 * Handles the process that happens when based on the input of the user.
 */
public class LogicHandler {
    /**
     * Triggered by the user typing 'list'.
     * Prints out all tasks in the list currently.
     * @param list List of tasks.
     */
    public void list(List<Task> list) {
        // Check if the command is list and display the list of duke.tasks.
        String temp = "";

        for (int i = 0; i < list.size(); i++) {
            temp += String.format("%d. %s", i + 1, list.get(i));
            if (i != list.size() - 1) {
                temp += "\n";
            }
        }
        System.out.println(temp);
    }

    /**
     * Updates the isDone status of a task of a specified index to true.
     * @param input string input of user
     * @param list list of tasks.
     */
    public void done(String input, List<Task> list) {
        try {
            int itemNumber = Integer.valueOf(input.split(" ")[1]) - 1;

            list.get(itemNumber).setIsDone();
            System.out.println(
                    "Nice! I've marked this task as done:\n" + list.get(itemNumber)
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops, your done command should have a task number behind.");
        } catch (NumberFormatException e) {
            System.out.println("Oops, your done command should be followed by an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops, your done command included an invalid index in the task list.");
        }
    }

    /**
     * Add a todo task to the list of tasks.
     * @param input string input of user.
     * @param list list of tasks.
     */
    public void todo(String input, List<Task> list) {
        try {
            Todo todo = new Todo(input.split(" ", 2)[1]);
            list.add(todo);
            System.out.println("added: " + todo);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops, your todo requires at least a description.");
        }
    }

    /**
     * Add a deadline task to the list of tasks.
     * @param input string input of user.
     * @param list list of tasks.
     */
    public void deadline(String input, List<Task> list) {
        try {
            String taskAndDate = input.split(" ", 2)[1];
            String task = taskAndDate.split(" /by ")[0];
            String date = taskAndDate.split(" /by ")[1];
            Deadline deadline = new Deadline(task, date);
            list.add(deadline);
            System.out.println("added: " + deadline);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops, your deadline requires both a description and date.");
        }
    }

    /**
     * Add an Event task to the list of tasks.
     * @param input string input of user.
     * @param list list of tasks.
     */
    public void event(String input, List<Task> list) {
        try {
            String taskAndDate = input.split(" ", 2)[1];
            String task = taskAndDate.split(" /at ")[0];
            String date = taskAndDate.split(" /at ")[1];
            Event event = new Event(task, date);
            list.add(event);
            System.out.println("added: " + event);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops, your Event requires both a description and date");
        }
    }

    /**
     * Deletes a task in the list of tasks given a specified task index integer.
     * @param input string input of user.
     * @param list list of tasks.
     */
    public void delete(String input, List<Task> list) {
        try {
            int index = Integer.valueOf(input.split(" ", 2)[1]);
            System.out.println("Noted. I've removed the task:\n" + list.get(index - 1) +
                    "\nNow you have " + (list.size() - 1) + " tasks in the list.");
            list.remove(index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Delete requires a number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}
