package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.common.DukeString;

/**
 * A class that represents a Task with a given deadline.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a new deadline task with the given description and date.
     *
     * @param desc the description of the task.
     * @param date the date of the deadline.
     */
    public DeadlineTask(final String desc, final LocalDateTime date) {
        super(desc);
        this.deadline = date;
    }

    /**
     * Constructs a new deadline task with the given status, description, and date.
     *
     * @param isDone the done status of the task.
     * @param desc the description of the task.
     * @param date the date of the deadline.
     */
    DeadlineTask(boolean isDone, String desc, LocalDateTime date) {
        super(desc);
        super.isDone = isDone;
        this.deadline = date;
    }

    @Override
    public String serialise() {
        return String.format(
                "%s\255%b\255%s\255%s",
                DukeString.COMMAND_DEADLINE,
                super.isDone,
                super.description,
                deadline.toString()
        );
    }

    /**
     * Constructs a new DeadlineTask given the serialised input read from storage.
     *
     * @param input the serialised input to be parsed.
     * @return a new DeadlineTask with the associated fields.
     */
    public static DeadlineTask deserialise(String input) {
        String[] tokens = input.split("\255");
        return new DeadlineTask(tokens[1].equals("true"), tokens[2], LocalDateTime.parse(tokens[3]));
    }

    @Override
    public String toString() {
        return String.format(
                DukeString.FORMAT_DEADLINE,
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern(DukeString.FORMAT_DATE_OUTPUT))
        );
    }
}
