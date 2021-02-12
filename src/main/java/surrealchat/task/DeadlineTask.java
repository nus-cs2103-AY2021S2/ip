package surrealchat.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    private DeadlineTask(String taskDescription, LocalDateTime deadline, boolean isDone, TaskPriority taskPriority) {
        super(taskDescription, "D", isDone, taskPriority);
        this.deadline = deadline;
    }

    /**
     * Creates new instance of DeadlineTask object.
     * @param taskDescription The description of task.
     * @param deadline LocalDate object of the date and time by which task should be completed.
     * @return New DeadlineTask that is not done.
     */
    public static DeadlineTask createNewDeadlineTask(
            String taskDescription, LocalDateTime deadline, TaskPriority taskPriority) {
        return new DeadlineTask(taskDescription, deadline, false, taskPriority);
    }

    /**
     * Creates instance of DeadlineTask based on what was loaded from file.
     * @param taskDescription The description of new task.
     * @param deadline The deadline of the task.
     * @param isDone Whether task was previously marked as done.
     * @return DeadlineTask as loaded from file.
     */
    public static DeadlineTask loadDeadlineTaskFromFile(
            String taskDescription, LocalDateTime deadline, boolean isDone, TaskPriority taskPriority) {
        return new DeadlineTask(taskDescription, deadline, isDone, taskPriority);
    }

    /**
     * Changes the description, deadline and priority of the ToDoTask.
     * @param newDescription New description of the task.
     * @param newDeadline New deadline of the task.
     * @param newPriority New task priority.
     * @return New ToDoTask with edited description, deadline and priority.
     */
    public DeadlineTask editTask(String newDescription, LocalDateTime newDeadline, TaskPriority newPriority) {
        return new DeadlineTask(newDescription, newDeadline, isDone, newPriority);
    }

    /**
     * Changes the description of the DeadlineTask.
     * @param newDescription The new description for the Task.
     * @return New DeadlineTask with edited description.
     */
    public DeadlineTask editDescription(String newDescription) {
        return editTask(newDescription, deadline, taskPriority);
    }

    /**
     * Changes the deadline of the DeadlineTask.
     * @param newDeadline The new deadline for the Task.
     * @return New DeadlineTask with edited deadline.
     */
    public DeadlineTask editDeadline(LocalDateTime newDeadline) {
        return editTask(description, newDeadline, taskPriority);
    }

    /**
     * Changes the priority of the DeadlineTask.
     * @param newPriority The new priority for the Task.
     * @return New DeadlineTask with edited priority.
     */
    public DeadlineTask editPriority(TaskPriority newPriority) {
        return editTask(description, deadline, newPriority);
    }

    /**
     * Toggles a DeadlineTask between done and undone.
     * @return Deadline that is marked as done/undone.
     */
    public DeadlineTask markAsDone() {
        return new DeadlineTask(getDescription(), deadline, !isDone, taskPriority);
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
