package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs new Event task.
     *
     * @param description Name of the event.
     * @param start Starting time of event.
     * @param end Ending time of event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns event task.
     *
     * @param taskInfo Information of the task.
     * @return New Event task.
     * @throws DukeWrongFormatException If taskInfo format is wrong.
     * @throws DukeMissingDescriptionException If taskInfo is blank.
     */
    public static Event create(String taskInfo) throws DukeWrongFormatException,
            DukeMissingDescriptionException {
        String[] parsedInfo = taskInfo.split(" /at ", 2);
        if (parsedInfo.length != 2) {
            throw new DukeWrongFormatException("event1");
        } else if (parsedInfo[0].equals(" ") || parsedInfo[1].equals(" ")) {
            throw new DukeMissingDescriptionException("event");
        } else {
            try {
                String[] parsedDate = parsedInfo[1].split(" ");
                String date = parsedDate[0];
                String[] parsedTime = parsedDate[1].split("-");
                LocalDateTime ldtStart = Parser.parseInputDate(date + " " + parsedTime[0]);
                LocalDateTime ldtEnd = Parser.parseInputDate(date + " " + parsedTime[1]);
                return new Event(parsedInfo[0], ldtStart, ldtEnd);
            } catch (Exception e) {
                throw new DukeWrongFormatException("event");
            }
        }
    }

    /**
     * Returns string of deadline name and info.
     * Format is for saving task into text file.
     *
     * @return string format of task's info.
     */
    @Override
    public String saveTask() {
        return String.format("E | %s | %s | %s-%s\n", super.getStatusIcon(),
                description, super.timeFormat(start), super.timeFormat(end));
    }

    /**
     * Returns string of event task name and info.
     * Format is for the display on the list.
     *
     * @return string format of task's info.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(),
                super.timeFormat(start), super.timeFormat(end));
    }
}
