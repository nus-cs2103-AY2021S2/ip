package duke.tasks;

import duke.DukeException;

public class Event extends Task {

    private final String location;

    public Event(String description, String location) {
        super(description, TaskType.EVENT);
        this.location = location;
    }

    public Event(String description, String location, Boolean completed) {
        super(description, TaskType.EVENT, completed);
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(" (at: ");
        output.append(this.location);
        output.append(")");
        return output.toString();
    }

    public String storageEntry() {
        StringBuilder output = new StringBuilder(super.storageEntry());
        output.append("|");
        output.append(this.location);
        return output.toString();
    }

    public static Event parseEvent(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!description.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The description of an event must contain a location.");
        }
        String[] partitioned = description.split("/at");
        return new Event(partitioned[0].strip(), partitioned[1].strip());
    }
}
