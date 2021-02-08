import java.time.LocalDate;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

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

    private boolean isTodo(Task task) {
        if (task instanceof Deadline || task instanceof Event) {
            return false;
        } else {
            return true;
        }
    }

    private LocalDate getTaskDate(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDate();
        } else {
            return ((Event) task).getDate();
        }
    }

}
