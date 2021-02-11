package duke.helper;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Helper class containing helper functions to assist our JUnit tests.
 */
public class HelperFunctions {

    /**
     * Does a deep comparison to check if the two input <code>TaskList</code> objects
     * are equal. The two lists are equal IFF their constituents have identical properties.
     *
     * @param taskListOne <code>TaskList</code> one
     * @param taskListTwo <code>TaskList</code> two
     * @return True is the two input <code>TaskList</code> objects are equal, and false otherwise.
     */
    public static boolean taskListsAreEqual(TaskList taskListOne, TaskList taskListTwo) {
        if (taskListOne.getSize() != taskListTwo.getSize()) {
            return false;
        }

        for (int index = 1; index <= taskListOne.getSize(); index++) {
            Task taskOne = taskListOne.getTaskByIndex(index);
            Task taskTwo = taskListTwo.getTaskByIndex(index);
            if (!tasksAreEqual(taskOne, taskTwo)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Does a deep copy of the input <code>TaskList</code>;
     *
     * @param tasks A <code>TaskList</code>
     * @return A deep copy <code>TaskList</code>, with its constituent <code>Task</code> objects
     * deep copies as well.
     */
    public static TaskList deepCopyTaskList(TaskList tasks) {
        TaskList newTasks = new TaskList();

        for (Task task : tasks.getListOfTasks()) {
            Task newTask;
            if (task instanceof ToDo) {
                newTask = new ToDo(task.getDescription());
            } else if (task instanceof Deadline) {
                newTask = new Deadline(task.getDescription(), ((Deadline) task).getByDateTime());
            } else if (task instanceof Event) {
                newTask = new Event(task.getDescription(), ((Event) task).getAtDateTime());
            } else {
                newTask = new Task(task.getDescription());
            }

            if (task.isDone()) {
                newTask.markAsDone();
            }

            newTasks.addTask(newTask);
        }

        return newTasks;
    }

    /**
     * Does a deep comparison to check if two input <code>Task</code> objects are equal.
     * The two tasks are equal IFF they have identical properties.
     *
     * @param taskOne <code>Task</code> one
     * @param taskTwo <code>Task</code> two
     * @return Return true if the two input <code>Task</code> objects are equal, and false otherwise.
     */
    public static boolean tasksAreEqual(Task taskOne, Task taskTwo) {
        if (!taskOne.getDescription().equals(taskTwo.getDescription())) {
            return false;
        }
        if (taskOne.isDone() != taskTwo.isDone()) {
            return false;
        }

        if (taskOne instanceof ToDo && !(taskTwo instanceof ToDo)) {
            return false;
        }

        if (taskOne instanceof Deadline && !(taskTwo instanceof Deadline)) {
            return false;
        }

        if (taskOne instanceof Event && !(taskTwo instanceof Event)) {
            return false;
        }

        if (taskOne instanceof Deadline) {
            String byDateTimeOne = ((Deadline) taskOne).getByDateTimeString();
            String byDateTimeTwo = ((Deadline) taskTwo).getByDateTimeString();
            if (!byDateTimeOne.equals(byDateTimeTwo)) {
                return false;
            }
        }

        if (taskOne instanceof Event) {
            String atDateTimeOne = ((Event) taskOne).getAtDateTimeString();
            String atDateTimeTwo = ((Event) taskTwo).getAtDateTimeString();
            if (!atDateTimeOne.equals(atDateTimeTwo)) {
                return false;
            }
        }

        return true;
    }

}
