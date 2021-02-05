package duke.processintructions;

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
     *
     * @param list List of tasks.
     */
    public String list(List<Task> list) {
        assert(list != null);

        String temp = "";

        for (int i = 0; i < list.size(); i++) {
            temp += String.format("%d. %s", i + 1, list.get(i));
            if (i != list.size() - 1) {
                temp += "\n";
            }
        }
        return temp;
    }

    /**
     * Updates the isDone status of a task of a specified index to true.
     * @param input String input of user.
     * @param list List of tasks.
     */
    public String done(String input, List<Task> list) {
        try {
            int itemNumber = Integer.valueOf(input.split(" ")[1]) - 1;

            list.get(itemNumber).setIsDone();
            return "Nice! I've marked this task as done:\n" + list.get(itemNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            return ("Oops, your done command should have a task number behind.");
        } catch (NumberFormatException e) {
            return ("Oops, your done command should be followed by an integer.");
        } catch (IndexOutOfBoundsException e) {
            return ("Oops, your done command included an invalid index in the task list.");
        }
    }

    /**
     * Add a todo task to the list of tasks.
     *
     * @param input String input of user.
     * @param list List of tasks.
     */
    public String todo(String input, List<Task> list) {
        try {
            Todo todo = new Todo(input.split(" ", 2)[1]);
            list.add(todo);
            return ("added: " + todo + "\n"
                    + "Now you have " + list.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Oops, your todo requires at least a description.";
        }
    }

    /**
     * Add a deadline task to the list of tasks.
     *
     * @param input String input of user.
     * @param list List of tasks.
     */
    public String deadline(String input, List<Task> list) {
        try {
            String taskAndDate = input.split(" ", 2)[1];
            String task = taskAndDate.split(" /by ")[0];
            String date = taskAndDate.split(" /by ")[1];
            Deadline deadline = new Deadline(task, date);
            list.add(deadline);
            return "added: " + deadline + "\n"
                    + "Now you have " + list.size() + " tasks in the list.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return ("Oops, your deadline requires both a description and date.");
        }
    }

    /**
     * Add an Event task to the list of tasks.
     *
     * @param input String input of user.
     * @param list List of tasks.
     */
    public String event(String input, List<Task> list) {
        try {
            String taskAndDate = input.split(" ", 2)[1];
            String task = taskAndDate.split(" /at ")[0];
            String date = taskAndDate.split(" /at ")[1];
            Event event = new Event(task, date);
            list.add(event);
            return "added: " + event + "\n"
                    + "Now you have " + list.size() + " tasks in the list.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Oops, your Event requires both a description and date";
        }
    }

    /**
     * Deletes a task in the list of tasks given a specified task index integer.
     *
     * @param input String input of user.
     * @param list List of tasks.
     */
    public String delete(String input, List<Task> list) {
        try {
            int index = Integer.valueOf(input.split(" ", 2)[1]);
            list.remove(index - 1);
            return ("Noted. I've removed the task:\n" + list.get(index - 1)
                    + "\nNow you have " + (list.size() - 1) + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            return ("Delete requires a number");
        } catch (IndexOutOfBoundsException e) {
            return ("Error: " + e);
        } catch (NumberFormatException e) {
            return ("Error: " + e);
        }
    }

    /**
     * Finds a task in the list of tasks given a specified String input.
     *
     * @param input String input of user.
     * @param list List of tasks.
     * @return A list of all tasks that contains the specified String input.
     */
    public String find(String input, List<Task> list) {
        try {
            Find find = new Find();

            String itemToFind = input.split(" ", 2)[1];
            List<Task> newList = find.contains(itemToFind, list);

            String temp = "";

            for (int i = 0; i < newList.size(); i++) {
                temp += String.format("%d. %s", i + 1, newList.get(i));
                if (i != newList.size() - 1) {
                    temp += "\n";
                }
            }
            return temp;
        } catch (ArrayIndexOutOfBoundsException e) {
            return ("Oops, your find requires a string description to find a match.");
        }
    }
}
