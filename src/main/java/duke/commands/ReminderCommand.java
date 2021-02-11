package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handles the logic of generating reminders w.r.t. the tasks in the to-do list.
 */
public class ReminderCommand extends Command {
    private final int urgencyDays;

    /**
     * Initializes a command to list out the overdue and urgent tasks in the to-do list.
     *
     * @param urgencyDays Number of days to use when determining if a <code>Deadline</code>
     *                    or an <code>Event</code> is urgent.
     */
    public ReminderCommand(int urgencyDays) {
        this.urgencyDays = urgencyDays;
    }

    /**
     * Does nothing.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
    }

    /**
     * Returns false as listing reminders should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Creates a response to display the existing tasks to the users. The output will
     * take the form of a multi-line string, with each row displaying one task with its
     * index, description and status.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> displaying the existing tasks.
     */
    public String getResponse(TaskList tasks) {
        return this.getOverdueTasksResponse(tasks) + "\n\n" + this.getUrgentTasksResponse(tasks, this.urgencyDays);
    }

    private String getOverdueTasksResponse(TaskList tasks) {
        TaskList overdueTasks = this.getOverdueTasks(tasks);
        int numOverdue = overdueTasks.getSize();

        if (numOverdue == 0) {
            return "You do not have any overdue tasks!";
        } else {
            return "Oh no... You have " + numOverdue + " overdue task(s):\n" + overdueTasks.getTaskListAsString();
        }
    }

    private String getUrgentTasksResponse(TaskList tasks, int urgencyDays) {
        TaskList urgentTasks = this.getUrgentTasks(tasks, urgencyDays);
        int numUrgent = urgentTasks.getSize();

        if (numUrgent == 0) {
            return "You do not have any urgent tasks!";
        } else {
            return "You have " + numUrgent + " task(s) that are due in less than " + urgencyDays + " days:\n"
                    + urgentTasks.getTaskListAsString();
        }
    }

    /**
     * Obtains a <code>TaskList</code> of overdue tasks.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A filtered <code>TaskList</code> comprising only the overdue tasks.
     */
    private TaskList getOverdueTasks(TaskList tasks) {
        TaskList overdueTasks = new TaskList();

        for (Task task : tasks.getListOfTasks()) {
            boolean overdue = false;

            if (task instanceof Event) {
                overdue = ((Event) task).isOverdue();
            } else if (task instanceof Deadline) {
                overdue = ((Deadline) task).isOverdue();
            }

            if (overdue) {
                overdueTasks.addTask(task);
            }
        }

        return overdueTasks;
    }

    /**
     * Obtains a <code>TaskList</code> of urgent tasks.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A filtered <code>TaskList</code> comprising only the urgent tasks.
     */
    private TaskList getUrgentTasks(TaskList tasks, int urgencyInDays) {
        assert urgencyInDays > 0;
        TaskList urgentTasks = new TaskList();

        for (Task task : tasks.getListOfTasks()) {
            boolean urgent = false;

            if (task instanceof Event) {
                urgent = ((Event) task).isUrgent(urgencyInDays);
            } else if (task instanceof Deadline) {
                urgent = ((Deadline) task).isUrgent(urgencyInDays);
            }

            if (urgent) {
                urgentTasks.addTask(task);
            }
        }

        return urgentTasks;
    }
}
