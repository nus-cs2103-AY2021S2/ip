package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks, and the relevant commands for the list, of the Duke.
 */
public class TaskList {
    protected ArrayList<Task> list = new ArrayList<>();

    protected TaskList() {
    }

    /**
     * Adds task to the list of tasks.
     *
     * @param task
     * @return task mainly for the Ui class' use. The user interface (Ui) needs to know the affected.
     * task to inform the user.
     */
    protected Task addTask(Task task) {
        list.add(task);
        return task;
    }

    /**
     * Sets the task as done.
     *
     * @param taskNum counting from numer 1, this is the number of the task that is done.
     * @return task mainly for the Ui class' use. The user interface (Ui) needs to know the affected.
     * task to inform the user.
     */
    protected Task doTask(int taskNum) {
        Task curr = list.get(taskNum - 1);
        curr.isDone = true;
        return curr;
    }

    /**
     * Sets the task as deleted
     *
     * @param num counting from numer 1, this is the number of the task that is deleted.
     * @return task mainly for the Ui class' use. The user interface (Ui) needs to know the affected.
     * task to inform the user.
     * @throws ArrayIndexOutOfBoundsException
     */
    protected Task delete(int num) throws ArrayIndexOutOfBoundsException {
        Task curr = list.get(num - 1);
        list.remove(num - 1);
        return curr;
    }

    /**
     * Turns off the duke and ends the program.
     *
     * @param duke
     */
    protected void bye(Duke duke) {
        duke.isOn = false;
    }
}
