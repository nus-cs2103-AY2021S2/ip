package duke.task;

import duke.Parser;

public class Deadline extends Task {
    private final String deadline;

    /**
     * Deadline constructor.
     *
     * @param content Task description
     * @param deadline String representation of the datetime the task has to be completed by.
     */
    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline string representation.
     * @return String representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by:%s)", Parser.parseDate(this.deadline.strip()));
    }

    /**
     * String representation of the deadline when it is stored in a file.
     * @return file string representation of the deadline
     */
    @Override
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + this.deadline;
    }
}
