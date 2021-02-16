package duke;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class Statistics { 
    final Queue<LocalDateTime> datetime;

    public Statistics() {
        this.datetime = new LinkedList<>();
    }

    public void addCompletionDatetime() {
        datetime.add(LocalDateTime.now());
    }

    public int getCompletedTasksCountLastWeek() {
        trimCompletionDatetimeLastWeek();
        return datetime.size();
    }

    private void trimCompletionDatetimeLastWeek() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dt = datetime.peek();
        while (dt != null) {
            if (!isLaterThanOneWeek(dt, currentDateTime)) {
                return;
            }

            datetime.poll();
            dt = datetime.peek();
        }
    }

    private static boolean isLaterThanOneWeek(LocalDateTime dt, LocalDateTime now) {
        assert(dt != null);
        long numberOfDays = ChronoUnit.DAYS.between(dt, now);
        return numberOfDays > 7;
    }

}
