package soonwee.duke;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Represents a DateTimeComparator instance. It compares the time of a task
 * with one another.
 */
public class DateTimeComparator implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		LocalDateTime time1 = task1.getDateTime();
		LocalDateTime time2 = task2.getDateTime();
		if (time1 != null && time2 != null) {
			if (time1.isBefore(time2)) {
				return -1;
			} else if (time1.isAfter(time2)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
	}
}
