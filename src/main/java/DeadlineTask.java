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
     * Changes the description of the DeadlineTask
     * @param newDescription New description of the task.
     * @return New DeadlineTask with edited description
     */
    public DeadlineTask editDescription(String newDescription) {
        return new DeadlineTask(newDescription, this.deadline, this.isDone);
    }

    /**
     * Marks a Deadline as done.
     * @return Deadline that is marked as done.
     */
    public DeadlineTask markAsDone() {
        if (!this.isDone) {
            return new DeadlineTask(this.getDescription(), this.deadline, true);
        } else {
            throw new UnsupportedOperationException("This task is already done.\n" +
                    "I would have wanted to say Stonks...\n" +
                    "but your usage of an illegal operation is Not Stonks!");
        }
    }

    /**
     * Marks a Deadline as undone.
     * @return Deadline that is marked as undone.
     */
    public DeadlineTask markAsUndone() {
        if (this.isDone) {
            return new DeadlineTask(this.getDescription(), this.deadline, false);
        } else {
            throw new UnsupportedOperationException("This task is already not done. Not stonks anyway!");
        }
    }

    /**
     * Converts the DeadlineTask into a string format for saving into file.
     * @return DeadlineTask in string format for file storage.
     */
    @Override
    public String saveTask() {
        return String.format("%s /by %s", super.saveTask(), this.deadline);
    }

    /**
     * Converts the DeadlineTask into a string format for display on user output.
     * @return DeadlineTask in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}
