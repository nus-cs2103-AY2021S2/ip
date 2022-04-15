package duketask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract task storing the task description,
 * state (whether it is done or not) and the time of creation
 * by the end-user.
 */
public abstract class Task {
    /**
     * Exception thrown when the user does not provide any
     * task description on construction.
     */
    public static class EmptyDescriptionException extends Exception {
        private static final long serialVersionUID = -365632185953600895L;

        public EmptyDescriptionException() {
            super("task description cannot be empty! no sense...");
        }
    }

    /**
     * Exception thrown when user attempts to mark the task as done
     * when the task is already in a done state.
     */
    public static class MarkedAsDoneException extends Exception {
        private static final long serialVersionUID = -7397628460804553793L;

        public MarkedAsDoneException() {
            super("trickery! task has already been marked done by dogeDuke...");
        }
    }

    /**
     * Enum switch state for whether the task is done or not.
     */
    protected enum TaskState {
        DONE, UNDONE;
    }

    protected String description;
    protected TaskState state;
    protected LocalDateTime createdDateTime;

    /**
     * Class main constructor.
     * @param description       the task description; cannot be empty.
     * @param isDone            whether the task is done on creation.
     * @param createdDateTime   the datetime at which the task is created.
     * @throws Task.EmptyDescriptionException
     */
    public Task (String description, boolean isDone, LocalDateTime createdDateTime)
            throws Task.EmptyDescriptionException {

        if (description.isEmpty()) {
            throw new Task.EmptyDescriptionException();
        }

        this.state = (isDone ? TaskState.DONE : TaskState.UNDONE);
        this.description = description;
        this.createdDateTime = createdDateTime;
    }

    /**
     * Class default constructor.
     * passes the undone state and current datetime for the task isDone and
     * createdDateTime parameters.
     * @param description       the task description; cannot be empty.
     * @throws Task.EmptyDescriptionException
     */
    public Task (String description) throws Task.EmptyDescriptionException {
        this(description, false, LocalDateTime.now());
    }

    // accessors
    public boolean isDone () {
        return (this.state == TaskState.DONE);
    }

    public boolean inDescription (String keyphrase) {
        return this.description.indexOf(keyphrase) >= 0;
    }

    /**
     * Returns information about the task.
     * @param outputFormat  the format in which to output any datetime
     *                      attributes as string representation.
     * @return      string containing the current state, the description
     *              and the datetime of creation in the format of the
     *              specified outputFormat.
     */
    public String getTaskInformation(DateTimeFormatter outputFormat) {
        return "[" + (this.state == TaskState.DONE ? "X" : " ")
                + "] " + this.description + " [ created: "
                + this.createdDateTime.format(outputFormat) + " ]";
    }

    /**
     * Returns a string command interpretable by the Parser class for future
     * reconstruction.
     * @param delimiter     the Parser delimiter used to separate blocks of
     *                      information.
     * @param parseFormat   the Parser format for reading datetime objects.
     * @return              string of parsable task information.
     */
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        // unique parsing sequence for Task
        return (this.isDone() ? 1 : 0) + delimiter + this.description
                + delimiter + this.createdDateTime.format(parseFormat);
    }

    // mutators
    /**
     * Switches the task from an undone to a done state. The task must not
     * already be in the done state.
     * @throws Task.MarkedAsDoneException
     */
    public void markAsDone () throws Task.MarkedAsDoneException {
        switch (this.state) {
        case DONE:
            throw new Task.MarkedAsDoneException();

        case UNDONE:
            this.state = TaskState.DONE;
            break;

        default:
            return;
        }
    }
}
