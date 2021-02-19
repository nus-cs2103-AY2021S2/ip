package lihua.commons.comparators;

import lihua.tasks.TimeTask;

/**
 * Comparator for TimeTask. This comparator compares two TimeTasks in chronological order.
 */
public class TimeTaskComparatorChronological implements java.util.Comparator<lihua.tasks.TimeTask> {
    /**
     * compareTo method for two tasks with time information.
     *
     * @param t1 The first TimeTask to compare.
     * @param t2 The second TimeTask to compare.
     * @return 1 if the first task is chronologically larger than second one, -1 otherwise. When there is a tie, compare
     * the two tasks based one their names' alphabetical order.
     */
    @Override
    public int compare(TimeTask t1, TimeTask t2) {
        int dateComparison = t1.getDate().compareTo(t2.getDate());
        if (dateComparison == 0) {
            return t1.compareTo(t2);
        }
        return dateComparison;
    }
}
