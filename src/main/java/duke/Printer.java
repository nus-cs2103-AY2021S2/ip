package duke;

import java.util.List;

import duke.task.Task;


public class Printer {
    /**
     * Returns Duke's reply to list command.
     *
     * @param tasks the task list
     * @return Duke's reply to list command
     * @throws DukeException
     */
    public static String printTaskList(List<Task> tasks) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There's currently no task in the list.");
        }
        String taskString = "";
        taskString += "" + (1) + "." + tasks.get(0).toString();
        for (int i = 1; i < tasks.size(); i++) {
            taskString += "\n" + (i + 1) + "." + tasks.get(i).toString();
        }
        return Ui.printFormatMessage(taskString);
    }

    /**
     * Returns Duke's reply to list command.
     *
     * @param doneTask the task to be marked as done
     * @return Duke's reply to list command
     */
    public static String printDoneReply(Task doneTask) {
        return Ui.printFormatMessage("Nice! I've marked this task as done:\n"
                + doneTask.toString());
    }

    /**
     * Returns Duke's reply to delete command.
     *
     * @param removedTask the task to be removed
     * @return Duke's reply to delete command
     */
    public static String printDeleteReply(Task removedTask) {
        return Ui.printFormatMessage("Noted. I've removed this task:\n"
                + removedTask.toString());
    }

    /**
     * Returns Duke's reply to find command.
     *
     * @param targetTasks the tasks match the target word
     * @return Duke's reply to find command
     */
    public static String printFindReply(List<Task> targetTasks) {
        if (targetTasks.size() == 0) {
            return "";
        }
        String taskString = "";
        taskString += "" + (1) + "." + targetTasks.get(0).toString();
        for (int i = 1; i < targetTasks.size(); i++) {
            taskString += "\n" + (i + 1) + "." + targetTasks.get(i).toString();
        }
        return Ui.printFormatMessage(taskString);
    }

    /**
     * Returns Duke's reply to todo or event or deadline command.
     *
     * @param newTask    the new task to be added
     * @param numOfTasks the number of tasks in the task list
     * @return Duke's reply to todo or event or deadline command.
     */
    public static String printAddReply(Task newTask, int numOfTasks) {
        return Ui.printFormatMessage("Got it. I've added this task:\n"
                + newTask.toString() + "\nNow you have " + numOfTasks
                + " tasks in the list.");
    }

}
