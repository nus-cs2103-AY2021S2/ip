package duke;

import java.util.Comparator;

class DoneComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        boolean isBothComplete = firstTask.isComplete() && secondTask.isComplete();
        boolean isBothIncomplete = !firstTask.isComplete() && !secondTask.isComplete();
        if (isBothComplete || isBothIncomplete) {
            NameComparator nameComparator = new NameComparator();
            return nameComparator.compare(firstTask, secondTask);
        }
        assert !(firstTask.isComplete() && secondTask.isComplete()) : "NameComparator Error";
        assert !(!firstTask.isComplete() && !secondTask.isComplete()) : "NameComparator Error";
        if (firstTask.isComplete()) {
            return -1;
        } else {
            return 1;
        }
    }
}
