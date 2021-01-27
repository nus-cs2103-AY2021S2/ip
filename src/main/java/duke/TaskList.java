package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> taskList;
    private final Ui ui = new Ui();

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
    public void addTask(Task newTask) {
        taskList.add(newTask);
        ui.printFormatMessage("\t  Got it. I've added this task:\n\t\t"
                + newTask.toString() + "\n\t  Now you have " + taskList.size()
                + " taskList in the list.");
    }

    /**
     * Deletes a task in the task list.
     *
     * @param order the order of the task to be deleted
     */
    public void delete(int order) {
        try {
            if (order < 0 || order >= taskList.size()) {
                throw new DukeException("There's no task "
                        + (order + 1) + " in the list.");
            }
            ui.printFormatMessage("\t  Noted. I've removed this task:\n"
                    + "\t\t " + taskList.get(order).toString());
            taskList.remove(order);
        } catch (DukeException ex) {
            ui.printFormatMessage(ex.toString());
        }
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param order the order of the task to be marked as done
     */
    public void markDone(int order) {
        try {
            if (order < 0 || order >= taskList.size()) {
                throw new DukeException("There's no task "
                        + (order + 1) + " in the list.");
            } else if (taskList.get(order).isDone()) {
                throw new DukeException("This task has been finished before.");
            }
            taskList.get(order).markDone();
            ui.printFormatMessage("\t  Nice! I've marked this task as done:\n\t\t "
                    + taskList.get(order).toString());
        } catch (DukeException ex) {
            ui.printFormatMessage(ex.toString());
        }

    }

    /**
     * Prints out all the tasks in task list currently.
     */
    public void printTaskList() {
        try {
            if (taskList.size() == 0) {
                throw new DukeException("There's currently no task in the list.");
            }
            String taskString = "";
            taskString += "\t  " + (1) + "." + taskList.get(0).toString();
            for (int i = 1; i < taskList.size(); i++) {
                taskString += "\n\t  " + (i + 1) + "." + taskList.get(i).toString();
            }
            ui.printFormatMessage(taskString);
        } catch (DukeException ex) {
            ui.printFormatMessage(ex.toString());
        }

    }

    /**
     * Prints out tasks in the given list.
     *
     * @param list the list given to be print out
     */
    public void printSpecifiedTasks(List<Task> list) {
        if (list.size() == 0) {
            return;
        }
        String taskString = "";
        taskString += "\t  " + (1) + "." + list.get(0).toString();
        for (int i = 1; i < list.size(); i++) {
            taskString += "\n\t  " + (i + 1) + "." + list.get(i).toString();
        }
        ui.printFormatMessage(taskString);
    }

    /**
     * Finds the tasks contain target keyword, and print them out.
     *
     * @param target the target keyword
     */
    public void findTask(String target) {
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
                printSpecifiedTasks(targetTasks);
            } else {
                throw new DukeException("There's currently no task name with \"" + target + "\".");
            }
        } catch (DukeException ex) {
            ui.printFormatMessage(ex.toString());
        }

    }

    public List<Task> getTaskList() {
        return taskList;
    }


}
