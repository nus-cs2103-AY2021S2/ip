package duke;

import java.util.Comparator;

/**
 * Custom comparator to sort tasks by whether they are completed, then by
 * alphabetical order.
 */
class DoneComparator implements Comparator<Task> {

    /**
     * Tasks which are completed will come before tasks which are not completed.
     * After that, tasks are sorted by ascending alphabetical order.
     *
     * @param firstTask The first task to compare.
     * @param secondTask The second task to compare.
     * @return An int value to indicate which task has higher priority.
     */
    @Override
    public int compare(Task firstTask, Task secondTask) {
        boolean isBothComplete = firstTask.isComplete() && secondTask.isComplete();
        boolean isBothIncomplete = !firstTask.isComplete() && !secondTask.isComplete();
        if (isBothComplete || isBothIncomplete) {
            NameComparator nameComparator = new NameComparator();
            return nameComparator.compare(firstTask, secondTask);
        }
        if (firstTask.isComplete()) {
            return -1;
        } else {
            return 1;
        }
    }
}
