package duke.logging;

import duke.exception.InvalidDescriptionException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.ToDo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A TaskList call denotes a system with a list of task and has the ability to make changes to the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Construct a TaskList.
     * @param tasks   A list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Print out all the tasks in the list.
     */
    public void list() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Add the task into the list.
     * @param type                          The type of task to be added into the list.
     * @param taskDescription               The description of the task.
     * @param ui                            A user interface.
     * @return                              The task that has been added to the list.
     * @throws InvalidDescriptionException  If the format of the command is wrong.
     * @throws DateTimeParseException       If the format of the dateTime is wrong.
     */
    public Task addTask(String type, String taskDescription, Ui ui)
            throws InvalidDescriptionException, DateTimeParseException {
        Task task;
        if (taskDescription.length() == 0) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description of " + type + " cannot be empty.");
        } else if (type.equals("todo")) {
            task = new ToDo(false, taskDescription);
        } else if ((type.equals("deadline") && !taskDescription.contains("/by"))
                || (type.equals("event")) && !taskDescription.contains("/at")) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description format of " + type + " is wrong.");
        } else {
            int index = type.equals("deadline")
                    ? taskDescription.indexOf("/by")
                    : taskDescription.indexOf("/at");
            String taskName = taskDescription.substring(0, index);
            String dateTimeString = taskDescription.substring(index + 4).strip()
                    .replace("/", "-");
            LocalDate dateTime = LocalDate.parse(dateTimeString);

            if (type.equals("deadline")) {
                task = new Deadline(false, taskName, dateTime);
            } else {
                task = new Event(false, taskName, dateTime);
            }
        }
        tasks.add(task);
        ui.addCommandInteraction(task, tasks);
        return task;
    }

    /**
     * Delete a task from the list.
     * @param taskDescription               The description of the task.
     * @param ui                            A user interface.
     * @return                              The list of tasks remaining.
     * @throws InvalidDescriptionException  If the format of the command is wrong.
     */
    public ArrayList<Task> delete(String taskDescription, Ui ui) throws InvalidDescriptionException{
        try {
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            ui.deleteCommandInteraction(task, tasks);
            return tasks;
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big "
                    + "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    /**
     * Completed a task in the list.
     * @param taskDescription                 The description of the task.
     * @param ui                              A user interface.
     * @return                                The list of tasks.
     * @throws InvalidDescriptionException    If the format of the command is wrong.
     */
    public ArrayList<Task> done(String taskDescription, Ui ui) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(taskDescription.substring(0, 1)) - 1;
            Task task = tasks.get(index);
            task.completeTask();
            ui.doneCommandInteraction(task);
            return tasks;
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big "
                    + "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    public void find(String taskDescription, Ui ui) throws InvalidDescriptionException {
        if (taskDescription.length() == 0) {
            throw new InvalidDescriptionException("☹ OOPS!!! The description cannot be empty.");
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.containSubstring(taskDescription.strip())) {
                matchingTasks.add(task);
            }
        }
        ui.findCommandInteraction(matchingTasks);
        for (Task task: matchingTasks) {
            System.out.println("     " + task);
        }
    }
}