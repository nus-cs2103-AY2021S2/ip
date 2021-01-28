package duke.task;

import duke.DukeException;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds new task in the task list.
     *
     * @param newTask the new task to be added
     */
    public String addTask(Task newTask) {
        taskList.add(newTask);
        return Ui.printFormatMessage("Got it. I've added this task:\n"
                + newTask.toString() + "\nNow you have " + taskList.size()
                + " taskList in the list.");
    }

    /**
     * Deletes a task in the task list.
     *
     * @param order the order of the task to be deleted
     */
    public String delete(int order) {
        try {
            if (order < 0 || order >= taskList.size()) {
                throw new DukeException("There's no task "
                        + (order + 1) + " in the list.");
            }
            Task removedTask = taskList.remove(order);
            return Ui.printFormatMessage("Noted. I've removed this task:\n"
                    + removedTask.toString());
        } catch (DukeException ex) {
            return Ui.printFormatMessage(ex.toString());
        }
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param order the order of the task to be marked as done
     */
    public String markDone(int order) {
        try {
            if (order < 0 || order >= taskList.size()) {
                throw new DukeException("There's no task "
                        + (order + 1) + " in the list.");
            } else if (taskList.get(order).isDone()) {
                throw new DukeException("This task has been finished before.");
            }
            taskList.get(order).markDone();
            return Ui.printFormatMessage("Nice! I've marked this task as done:\n"
                    + taskList.get(order).toString());
        } catch (DukeException ex) {
            return Ui.printFormatMessage(ex.toString());
        }

    }

    /**
     * Prints out all the tasks in task list currently.
     */
    public String printTaskList() {
        try {
            if (taskList.size() == 0) {
                throw new DukeException("There's currently no task in the list.");
            }
            String taskString = "";
            taskString += "" + (1) + "." + taskList.get(0).toString();
            for (int i = 1; i < taskList.size(); i++) {
                taskString += "\n" + (i + 1) + "." + taskList.get(i).toString();
            }
            return Ui.printFormatMessage(taskString);
        } catch (DukeException ex) {
            return Ui.printFormatMessage(ex.toString());
        }

    }

    /**
     * Prints out tasks in the given list.
     *
     * @param list the list given to be print out
     */
    public String printSpecifiedTasks(List<Task> list) {
        if (list.size() == 0) {
            return "";
        }
        String taskString = "";
        taskString += "" + (1) + "." + list.get(0).toString();
        for (int i = 1; i < list.size(); i++) {
            taskString += "\n" + (i + 1) + "." + list.get(i).toString();
        }
        return Ui.printFormatMessage(taskString);
    }

    /**
     * Finds the tasks contain target keyword, and print them out.
     *
     * @param target the target keyword
     */
    public String findTask(String target) {
        try {
            if (taskList.size() == 0) {
                throw new DukeException("There's currently no task in the list.");
            }
            List<Task> targetTasks = new ArrayList<>();
            for (Task t : taskList) {
                if (t.getName().contains(target)) {
                    targetTasks.add(t);
                }
            }
            if (targetTasks.size() != 0) {
                return printSpecifiedTasks(targetTasks);
            } else {
                throw new DukeException("There's currently no task name with \"" + target + "\".");
            }
        } catch (DukeException ex) {
            return Ui.printFormatMessage(ex.toString());
        }

    }

    public List<Task> getTaskList() {
        return taskList;
    }


}
