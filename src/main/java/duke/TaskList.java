package duke;

import java.util.ArrayList;
import java.util.List;

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
     * Add more task.
     *
     * @param newTask Append the newTask to the end of the task list.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        ui.printFormatMessage("\t  Got it. I've added this task:\n\t\t" + newTask.toString() + "\n\t  Now you have " + taskList.size() + " taskList in the list.");
    }

    /**
     * Delete the task.
     *
     * @param order the order of the task
     */
    public void delete(int order) {
        try {
            if (order < 0 || order >= taskList.size()) {
                throw new DukeException("There's no task " + (order + 1) + " in the list.");
            }
            ui.printFormatMessage("\t  Noted. I've removed this task:\n" + "\t\t " + taskList.get(order).toString());
            taskList.remove(order);
        } catch (DukeException ex) {
            ui.printFormatMessage(ex.toString());
        }
    }

    /**
     * Mark the task as done.
     *
     * @param order the order of the task
     */
    public void markDone(int order) {
        try {
            if (order < 0 || order >= taskList.size()) {
                throw new DukeException("There's no task " + (order + 1) + " in the list.");
            } else if (taskList.get(order).getDone()) {
                throw new DukeException("This task has been finished before.");
            }
            taskList.get(order).markDone();
            ui.printFormatMessage("\t  Nice! I've marked this task as done:\n\t\t " + taskList.get(order).toString());
        } catch (DukeException ex) {
            ui.printFormatMessage(ex.toString());
        }

    }

    public void printTaskList() {
        try {
            if (taskList.size() == 0) {
                throw new DukeException("There's currently no any taskList.");
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

    public List<Task> getTaskList() {
        return taskList;
    }


}
