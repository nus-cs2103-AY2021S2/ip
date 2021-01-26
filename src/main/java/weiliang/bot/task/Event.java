package weiliang.bot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime timing;

    public Event(String task, String timing) {
        super(task);
        this.timing = LocalDateTime.parse(timing);
    }

    @Override
    public String toString() {
        return "[E][" + (completed ? "X" : " ") + "] " + task + " (at: "
                + timing.format(DateTimeFormatter.ofPattern("dd MMMM YYYY, hh:mma")) + ")";
    }

}
