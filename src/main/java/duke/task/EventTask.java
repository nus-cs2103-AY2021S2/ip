package duke.task;

import duke.common.DukeString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public EventTask(final String desc, final LocalDateTime start, final LocalDateTime end) {
        super(desc);
        this.startDate = start;
        this.endDate = end;
    }

    private EventTask(boolean done, String desc, String date) {
        super(desc);
        super.isDone = done;
        this.event = date;
    }

    @Override
    public String serialise() {
        return String.format(
                "%s\255%b\255%s\255%s",
                DukeString.COMMAND_EVENT,
                super.isDone,
                super.description,
                event
        );
    }

    public static EventTask deserialise(String input) {
        String[] tokens = input.split("\255");
        return new EventTask(tokens[1].equals("true"), tokens[2], tokens[3]);
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to %s)",
                super.toString(),
                startDate.format(DateTimeFormatter.ofPattern("dd LLL uu hhmma")),
                endDate.format(DateTimeFormatter.ofPattern("dd LLL uu hhmma"))
        );
    }
}
