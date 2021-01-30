package prerthan.duke.task;

import java.io.IOException;
import java.util.ArrayList;

import prerthan.duke.Duke;
import prerthan.duke.exception.DukeEmptyDetailException;

/**
 * A Task class that allows creating a static list of tasks, that can be added
 * to, iterated through and polled for contents, or deleted from.
 * <p>
 * Also allows initialising an instance of a Task, which come with appropriate
 * instance methods.
 */
public abstract class Task {
    // A list of tasks.
    protected static ArrayList<Task> tasks;

    protected String detail;
    protected boolean isComplete;

    Task() {
        this.isComplete = false;
    }

    /**
     * Initialises a {@link Task} with some specified {@code detail}, and is set as
     * incomplete.
     * 
     * @param name the task detail
     * @throws DukeEmptyDetailException if {@code detail} is blank, as specified by
     *                                  {@link String#isBlank()}.
     */
    protected Task(String detail) throws DukeEmptyDetailException {
        this();

        if (detail.isBlank())
            throw new DukeEmptyDetailException(this.getClass().getName());

        this.detail = detail;
    }

    /**
     * Returns a character representing the completion state of this task.
     * 
     * @return {@code '✔'} if complete, {@code '✘'} otherwise
     */
    public char getCompleteIcon() {
        return isComplete ? '✔' : '✘';
    }

    /**
     * Marks this task as complete, and returns the state of the task (must be
     * {@code true}).
     * 
     * @return {@code true} if complete
     * @throws IOException
     */
    public boolean markComplete() {
        this.isComplete = true;
        Duke.output.sayTaskMarkedComplete(this);

        return isComplete;
    }

    /**
     * Returns a character representing the type of Task (To-Do, Deadline, or
     * Event).
     * 
     * @return the character representing the task type
     */
    public abstract char getTaskTypeIcon();

    public abstract String encode();

    @Override
    public String toString() {
        return String.format("[%c]\t %s", this.getCompleteIcon(), this.detail);
    }
}