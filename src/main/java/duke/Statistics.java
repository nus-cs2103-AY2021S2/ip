package duke;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Statistics {
    final Queue<LocalDateTime> datetime;


    /**
     * Sole constructor for Statistics class.
     *
     * @param   dateTime    the list of datetime the user has completed.
     **/
    public Statistics(List<LocalDateTime> dateTime) {
        this.datetime = new LinkedList<>();
        this.datetime.addAll(dateTime);
    }

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

    public List<String> encode() {
        return datetime.stream().map(dt -> dt.toString()).collect(Collectors.toList());
    }

}
