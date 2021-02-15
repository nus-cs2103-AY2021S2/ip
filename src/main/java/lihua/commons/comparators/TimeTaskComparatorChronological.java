package lihua.commons.comparators;

import lihua.tasks.TimeTask;

public class TimeTaskComparatorChronological implements java.util.Comparator<lihua.tasks.TimeTask> {
    @Override
    public int compare(TimeTask t1, TimeTask t2) {
        int dateComparison = t1.getDate().compareTo(t2.getDate());
        if (dateComparison == 0) {
            return t1.compareTo(t2);
        }
        return dateComparison;
    }
}
