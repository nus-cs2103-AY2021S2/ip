package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Event task which is an extension os Task to also include a location of the event
 */
public class Event extends Task {

    private final String location;

    /**
     * Constructor for the Event class
     *
     * @param description of the Event
     * @param location    of the Event
     */
    public Event(String description, String location) {
        super(description, TaskType.EVENT);
        this.location = location;
    }

    /**
     * Constructor for the Event class with completion status specified
     *
     * @param description of the Event
     * @param location    of the Event
     * @param completed   <code>true</code> if the event is completed, <code>false</code> otherwise
     */
    public Event(String description, String location, Boolean completed) {
        super(description, TaskType.EVENT, completed);
        this.location = location;
    }

    /**
     * Parse command meant to create Event tasks
     *
     * @param command to be parsed
     * @return Event object created based on command issued
     * @throws DukeException if command is empty or missing "/at" delimiter which provides the location of the event
     */
    public static Event parseEvent(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!command.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The description of an event must contain a location.");
        }
        String[] partitioned = command.split("/at");
        return new Event(partitioned[0].strip(), partitioned[1].strip());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(" (at: ");
        output.append(this.location);
        output.append(")");
        return output.toString();
    }

    /**
     * Returns the String formatted entry for writing to disk
     *
     * @return String formatted entry to be written to disk by Storage objects
     */
    public String storageEntry() {
        StringBuilder output = new StringBuilder(super.storageEntry());
        output.append("|");
        output.append(this.location);
        return output.toString();
    }
}
