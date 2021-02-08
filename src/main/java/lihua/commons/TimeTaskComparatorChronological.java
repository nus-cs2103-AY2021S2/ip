package lihua.commons;

import lihua.tasks.TimeTask;

public class TimeTaskComparatorChronological implements java.util.Comparator<lihua.tasks.TimeTask> {
    @Override
    public int compare(TimeTask t1, TimeTask t2) {
        return t1.getDate().compareTo(t2.getDate());
    }
}
