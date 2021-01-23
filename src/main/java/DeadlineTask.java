package surrealchat.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate deadline;

    /**
     * Creates instance of DeadlineTask object.
     * @param taskDescription The description of task.
     * @param deadline LocalDate object of the date by which task should be completed.
     * @param isDone Boolean flag of whether task is done or not.
     */
    public DeadlineTask(String taskDescription, LocalDate deadline, boolean isDone) {
        super(taskDescription, "D", isDone);
        this.deadline = deadline;
    }

    /**
     * Converts the task into a string format for saving into file.
     * @return Task in string format for file storage.
     */
    @Override
    public String saveTask() {
        return String.format("%s /by %s", super.saveTask(), this.deadline);
    }

    /**
     * Converts the task into a string format for display on user output.
     * @return Task in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}
