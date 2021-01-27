package duke.task;

import duke.DukeHelper;

import java.time.LocalDate;

public class Event extends Task {

    private LocalDate at;

    public LocalDate getAt() {
        return at;
    }

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public Event(int done, String description, String at) {
        super(done, description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DukeHelper.formatDate(at));
    }

    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }
}
