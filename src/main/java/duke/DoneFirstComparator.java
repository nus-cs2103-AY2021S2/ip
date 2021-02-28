package duke;

import java.util.Comparator;

/**
 * Custom comparator to sort tasks by completed tasks first, then by
 * alphabetical order.
 */
class DoneFirstComparator implements Comparator<Task> {

    /**
     * Tasks are sorted by completed tasks first before incomplete tasks, and
     * then they are sorted by ascending alphabetical order.
     *
     * @param firstTask The first task to be compared.
     * @param secondTask The second task to be compared.
     * @return -1 if the first task has higher priority and 1 otherwise.
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
