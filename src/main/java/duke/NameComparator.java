package duke;

import java.util.Comparator;

/**
 * Custom comparator to sort tasks by ascending alphabetical order.
 */
class NameComparator implements Comparator<Task> {

    /**
     * Compare tasks according to ascending alphabetical order.
     * @param firstTask The first task to be compared.
     * @param secondTask The second task to compared.
     * @return -1 if the first task has higher priority and 1 otherwise.
     */
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getTaskName().compareTo(secondTask.getTaskName());
    }
}
