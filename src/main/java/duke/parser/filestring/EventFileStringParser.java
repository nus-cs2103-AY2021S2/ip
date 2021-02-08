package duke.parser.filestring;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Parser class to read and write Event to file.
 */
public class EventFileStringParser extends TaskFileStringParser {

    /**
     * Generates file string for Event.
     *
     * @param task Event
     * @return string representation of event to be written to file
     */
    public String toFileString(Task task) {
        Event event = (Event) task;
        String description = event.getDescription();
        String datetime = event.getDatetimeString();
        String done = event.getDone() ? "1" : "0";
        return String.format("event\t%s\t%s\t%s", description, datetime, done);
    }

    /**
     * Reads file string for Event.
     *
     * @param eventFileString string representation of Event
     * @return Event
     * @throws DukeExceptionIllegalArgument When Event parsing fails
     */
    @Override
    public Task fromFileString(String eventFileString) throws DukeExceptionIllegalArgument {
        String[] data = eventFileString.split("\t");
        assert data.length == 4;
        assert data[0].equals("event");
        String description = data[1];
        String datetime = data[2];
        boolean done = data[3].equals("1");
        return new Event(description, datetime, done);
    }
}
