package surrealchat.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    private DeadlineTask(boolean isDone, String taskDescription, TaskPriority taskPriority, LocalDateTime deadline) {
        super(TaskCode.DEADLINE_TYPE, isDone, taskDescription, taskPriority);
        this.deadline = deadline;
    }

    /**
     * Creates new instance of DeadlineTask object.
     * @param taskDescription The description of task.
     * @param taskPriority Priority of task.
     * @param deadline LocalDate object of the date and time by which task should be completed.
     * @return New DeadlineTask that is not done.
     */
    public static DeadlineTask createNewDeadlineTask(
            String taskDescription, TaskPriority taskPriority, LocalDateTime deadline) {
        return new DeadlineTask(false, taskDescription, taskPriority, deadline);
    }

    /**
     * Creates instance of DeadlineTask based on what was loaded from file.
     * @param isDone Whether task was previously marked as done.
     * @param deadline The deadline of the task.
     * @param taskDescription The description of new task.
     * @param taskPriority Priority of task.
     * @return DeadlineTask as loaded from file.
     */
    public static DeadlineTask loadDeadlineTaskFromFile(
            boolean isDone, String taskDescription, TaskPriority taskPriority, LocalDateTime deadline) {
        return new DeadlineTask(isDone, taskDescription, taskPriority, deadline);
    }

    /**
     * Changes the description, deadline and priority of the DeadlineTask.
     * @param newDescription New description of the task.
     * @param newPriority New task priority.
     * @param newDeadline New deadline of the task.
     * @return New ToDoTask with edited description, deadline and priority.
     */
    public DeadlineTask editTask(String newDescription, TaskPriority newPriority, LocalDateTime newDeadline) {
        return new DeadlineTask(isDone, newDescription, newPriority, newDeadline);
    }

    /**
     * Changes the description of the DeadlineTask.
     * @param newDescription The new description for the Task.
     * @return New DeadlineTask with edited description.
     */
    public DeadlineTask editDescription(String newDescription) {
        return editTask(newDescription, taskPriority, deadline);
    }

    /**
     * Changes the deadline of the DeadlineTask.
     * @param newDeadline The new deadline for the Task.
     * @return New DeadlineTask with edited deadline.
     */
    public DeadlineTask editDeadline(LocalDateTime newDeadline) {
        return editTask(description, taskPriority, newDeadline);
    }

    /**
     * Changes the priority of the DeadlineTask.
     * @param newPriority The new priority for the Task.
     * @return New DeadlineTask with edited priority.
     */
    public DeadlineTask editPriority(TaskPriority newPriority) {
        return editTask(description, newPriority, deadline);
    }

    /**
     * Toggles a DeadlineTask between done and undone.
     * @return Deadline that is marked as done/undone.
     */
    public DeadlineTask markAsDone() {
        return new DeadlineTask(!isDone, getDescription(), taskPriority, deadline);
    }


    /**
     * Converts the DeadlineTask into a string format for saving into file.
     * @return DeadlineTask in string format for file storage.
     */
    @Override
    public String saveTask() {
        return String.format("%s /by %s", super.saveTask(), deadline);
    }

    /**
     * Converts the DeadlineTask into a string format for display on user output.
     * @return DeadlineTask in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s, %s)",
                super.toString(), deadline.toLocalDate(), deadline.toLocalTime());
    }
}
