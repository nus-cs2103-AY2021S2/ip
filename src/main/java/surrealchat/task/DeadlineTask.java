package surrealchat.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate deadline;

    private DeadlineTask(String taskDescription, LocalDate deadline, boolean isDone) {
        super(taskDescription, "D", isDone);
        this.deadline = deadline;
    }

    /**
     * Creates new instance of DeadlineTask object.
     * @param taskDescription The description of task.
     * @param deadline LocalDate object of the date by which task should be completed.
     * @return New DeadlineTask that is not done.
     */
    public static DeadlineTask createNewDeadlineTask(String taskDescription, LocalDate deadline) {
        return new DeadlineTask(taskDescription, deadline, false);
    }

    /**
     * Creates instance of DeadlineTask based on what was loaded from file.
     * @param taskDescription The description of new task.
     * @param deadline The deadline of the task.
     * @param isDone Whether task was previously marked as done.
     * @return DeadlineTask as loaded from file.
     */
    public static DeadlineTask loadDeadlineTaskFromFile(String taskDescription, LocalDate deadline, boolean isDone) {
        return new DeadlineTask(taskDescription, deadline, isDone);
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
     * Toggles a DeadlineTask between done and undone.
     * @return Deadline that is marked as done/undone.
     */
    public DeadlineTask markAsDone() {
        return new DeadlineTask(this.getDescription(), this.deadline, !this.isDone);
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
