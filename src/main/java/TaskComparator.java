import java.time.LocalDate;
import java.util.Comparator;

/**
 * Represents a task comparator object that implements a comparator on a Task object.
 * Contains a compare method with additional helper methods.
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares the two tasks.
     * Prioritizes events/deadlines that are not done yet.
     * Prioritizes earlier dates.
     *
     * @param taskOne Task to be compared.
     * @param taskTwo Task to be compared.
     * @return Integer representing which task should be prioritized.
     */
    @Override
    public int compare(Task taskOne, Task taskTwo) {
        String taskOneDescription = taskOne.getDescription();
        String taskTwoDescription = taskTwo.getDescription();
        boolean taskOneHasDate = !isTodo(taskOne);
        boolean taskTwoHasDate = !isTodo(taskTwo);
        LocalDate taskOneDate = LocalDate.now();
        LocalDate taskTwoDate = LocalDate.now();
        if (taskOneHasDate) {
            taskOneDate = getTaskDate(taskOne);
        }
        if (taskTwoHasDate) {
            taskTwoDate = getTaskDate(taskTwo);
        }

        if (taskOne.isDone() && !taskTwo.isDone()) {
            return 1;
        } else if (!taskOne.isDone() && taskTwo.isDone()) {
            return -1;
        } else if (taskOneHasDate && taskTwoHasDate) {
            int dateComparison = taskOneDate.compareTo(taskTwoDate);
            if (dateComparison == 0) {
                return taskOneDescription.compareTo(taskTwoDescription);
            } else {
                return dateComparison;
            }
        } else if (taskOneHasDate) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Checks if task is a Todo-task.
     *
     * @param task Task to check.
     * @return Boolean representing whether the task is a Todo-task.
     */
    protected boolean isTodo(Task task) {
        if (task instanceof Deadline || task instanceof Event) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Obtains Date of Event/Deadline-task.
     *
     * @param task Task to obtain date of.
     * @return LocalDate representing the date associated with this task.
     */
    protected LocalDate getTaskDate(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDate();
        } else {
            return ((Event) task).getDate();
        }
    }

}
