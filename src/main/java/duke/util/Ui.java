package duke.util;

import duke.task.Task;
import duke.util.TaskList;

import java.util.ListIterator;

/**
 * Class containing methods that interacts with the user.
 */
public class Ui {
    public String formatAddCmdMsg(Task taskAdded, TaskList tasks) {
        return ("Got it. I've added this task:\n" + taskAdded +
                "\nYou have " +
                tasks.getSize() + (tasks.getSize() == 1 ? " task" : " tasks") + " in your list");
    }

    public String formatRemoveCmdMsg(Task taskRemoved, TaskList tasks) {
        return ("I've removed this task:\n" + taskRemoved +
                "\nYou have " +
                tasks.getSize() + (tasks.getSize() == 1 ? " task" : " tasks") + " in your list");
    }

    public String formatDoneCmdMsg(Task taskDone) {
        return ("Nice! I have marked this task as done:\n" + taskDone);
    }

    public String formatFindCmdMsg(TaskList matchingTasks) {
        if (matchingTasks.getSize() == 0) {
            return "There are no tasks with such keyword!";
        }
        ListIterator<Task> iterator = matchingTasks.getIterator();
        String msg = "I have found tasks with the given keyword:\n";
        while (iterator.hasNext()) {
            Task task = iterator.next();
            msg += (task + (iterator.hasNext() ? "\n" : ""));
        }
        return msg;
    }

    public String formatListCmdMsg(TaskList allTasks) {
        if (allTasks.getSize() == 0) {
            return "There are no tasks to display!";
        }
        ListIterator<Task> iterator = allTasks.getIterator();
        String msg = "Your tasks:\n";
        int count = 1;
        while (iterator.hasNext()) {
            Task task = iterator.next();
            msg += (count + "\t" + count + "." +  task
                    + (iterator.hasNext() ? "\n" : ""));
            count++;
        }
        return msg;
    }
}
