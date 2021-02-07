package duke;

import java.util.Comparator;

class NameComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getTaskName().compareTo(secondTask.getTaskName());
    }
}
