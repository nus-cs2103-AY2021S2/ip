package duke.logging;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.InvalidDescriptionException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.ToDo;

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
    public String list() {
        String message = "     Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            message += "\n" + "     " + (i + 1) + "." + tasks.get(i);
        }
        return message;
    }

    /**
     * Add a task into the list of tasks.
     * @param type                         The type of task.
     * @param taskDescription              The description of the task.
     * @param storage                      The storage information containing the list of tasks information.
     * @return                             The updated list of tasks.
     * @throws InvalidDescriptionException If description format is wrong.
     * @throws DateTimeParseException      If datetime format is wrong.
     * @throws IOException                 If inputting information into the storage post an error.
     */
    public ArrayList<Task> addTask(String type, String taskDescription, Storage storage)
            throws InvalidDescriptionException, DateTimeParseException, IOException {
        Task task;
        if (taskDescription.length() == 0) {
            throw new InvalidDescriptionException("OOPS!!! The description of " + type + " cannot be empty.");
        }else if ((type.equals("deadline") && !taskDescription.contains("/by"))
                || (type.equals("event")) && !taskDescription.contains("/at")) {
            throw new InvalidDescriptionException("OOPS!!! The description format of " + type + " is wrong.");
        }

        if (type.equals("todo")) {
            task = new ToDo(false, taskDescription);
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
        storage.append(task);
        return tasks;
    }

    /**
     * Get the indicated task.
     * @param index                          The index of the task.
     * @return                               The indicated task.
     * @throws InvalidDescriptionException   If index > tasks.size()
     */
    public Task getTask(int index) throws InvalidDescriptionException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("Description cannot be empty");
        }
    }

    /**
     * Delete a task from the list of tasks.
     * @param index                           The index of the task.
     * @param storage                         The storage information containing the list of tasks information.
     * @return                                The updated list of tasks.
     * @throws InvalidDescriptionException    If description format is wrong.
     * @throws IOException                    If inputting information into the storage post an error.
     */
    public ArrayList<Task> delete(int index, Storage storage) throws InvalidDescriptionException, IOException {
        try {
            tasks.remove(index);
            storage.overwrite(tasks);
            return tasks;
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("OOPS!!! The number you entered is either too big "
                    + "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    /**
     * Complete a task from the list of tasks.
     * @param index                          The index of the task.
     * @param storage                        The storage information containing the list of tasks information.
     * @return                               The updated list of tasks
     * @throws InvalidDescriptionException   If description format is wrong.
     * @throws IOException                   If inputting information into the storage post an error.
     */
    public ArrayList<Task> done(int index, Storage storage) throws InvalidDescriptionException, IOException {
        try {
            Task task = this.getTask(index);
            task.completeTask();
            storage.overwrite(tasks);
            return tasks;
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The task description is wrong");
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException("☹ OOPS!!! The number you entered is either too big "
                    + "or smaller than 0. There are currently " + tasks.size() + " tasks");
        }
    }

    /**
     * Find a task from the list of tasks.
     * @param taskDescription               The description of the task.
     * @return                              The list of tasks matching the task description.
     * @throws InvalidDescriptionException  If the description is empty.
     */
    public ArrayList<Task> find(String taskDescription) throws InvalidDescriptionException {
        if (taskDescription.length() == 0) {
            throw new InvalidDescriptionException("OOPS!!! The description cannot be empty.");
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.containSubstring(taskDescription.strip())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
