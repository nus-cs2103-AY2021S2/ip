package duke;

import java.util.Comparator;

/**
 * Custom comparator to sort tasks by ascending alphabetical order.
 */
class NameComparator implements Comparator<Task> {

    /**
     * Compare tasks according to ascending alphabetical order.
     * @param firstTask The first task to compare.
     * @param secondTask The second task to compare.
     * @return An int value to indicate which task has higher priority.
     */
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getTaskName().compareTo(secondTask.getTaskName());
    }
}
