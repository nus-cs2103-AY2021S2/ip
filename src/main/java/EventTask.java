package surrealchat.task;

import java.time.LocalDate;

/**
 * Represents a task that can occur only at a certain time.
 */
public class EventTask extends Task {
    private LocalDate event;

    /**
     * Creates instance of EventTask object.
     * @param taskDescription The description of task.
     * @param event LocalDate object of the date at which task should happen.
     * @param isDone Boolean flag of whether task is done or not.
     */
    public EventTask(String taskDescription, LocalDate event, boolean isDone) {
        super(taskDescription, "E", isDone);
        this.event = event;
    }

    /**
     * Changes the description of the EventTask
     * @param newDescription New description of the task.
     * @return New EventTask with edited description
     */
    public EventTask editDescription(String newDescription) {
        return new EventTask(newDescription, this.event, this.isDone);
    }

    /**
     * Marks an EventTask as done.
     * @return EventTask that is marked as done
     */
    public EventTask markAsDone() {
        if (!this.isDone) {
            return new EventTask(this.getDescription(), this.event, true);
        } else {
            throw new UnsupportedOperationException("This task is already done.\n" +
                    "I would have wanted to say Stonks...\n" +
                    "but your usage of an illegal operation is Not Stonks!");
        }
    }

    /**
     * Marks an EventTask as undone.
     * @return EventTask that is marked as undone.
     */
    public EventTask markAsUndone() {
        if (this.isDone) {
            return new EventTask(this.getDescription(), this.event, false);
        } else {
            throw new UnsupportedOperationException("This task is already not done. Not stonks anyway!");
        }
    }

    /**
     * Converts the EventTask into a string format for saving into file.
     * @return EventTask in string format for file storage.
     */
    @Override
    public String saveTask() {
        return String.format("%s /at %s", super.saveTask(), this.event);
    }

    /**
     * Converts the EventTask into a string format for display on user output.
     * @return EventTask in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.event);
    }
}
