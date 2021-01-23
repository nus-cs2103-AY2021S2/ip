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
     * Converts the task into a string format for saving into file.
     * @return Task in string format for file storage.
     */
    @Override
    public String saveTask() {
        return String.format("%s /at %s", super.saveTask(), this.event);
    }

    /**
     * Converts the task into a string format for display on user output.
     * @return Task in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.event);
    }
}
