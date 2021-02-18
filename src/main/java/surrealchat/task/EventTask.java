package surrealchat.task;

import java.time.LocalDateTime;

/**
 * Represents a task that can occur only at a certain time.
 */
public class EventTask extends Task {
    private LocalDateTime event;

    private EventTask(boolean isDone, String taskDescription, TaskPriority taskPriority, LocalDateTime event) {
        super(TaskCode.EVENT_TYPE, isDone, taskDescription, taskPriority);
        this.event = event;
    }

    /**
     * Creates new instance of EventTask object.
     *
     * @param taskDescription The description of task.
     * @param taskPriority Priority of task.
     * @param event LocalDateTime object of the date and time at which task should happen.
     * @return New EventTask that is not done.
     */
    public static EventTask createNewEventTask(
            String taskDescription, TaskPriority taskPriority, LocalDateTime event) {
        return new EventTask(false, taskDescription, taskPriority, event);
    }

    /**
     * Creates instance of EventTask based on what was loaded from file.
     *
     * @param isDone Whether task was previously marked as done.
     * @param taskDescription The description of new task.
     * @param taskPriority Priority of task.
     * @param event The event date and time of the task.
     * @return EventTask as loaded from file.
     */
    public static EventTask loadEventTaskFromFile(
            boolean isDone, String taskDescription, TaskPriority taskPriority, LocalDateTime event) {
        return new EventTask(isDone, taskDescription, taskPriority, event);
    }

    /**
     * Changes the description, event datetime and priority of the EventTask.
     *
     * @param newDescription New description of the task.
     * @param newPriority New task priority.
     * @param newEventDate New event datetime of the task.
     * @return New ToDoTask with edited description, event datetime and priority.
     */
    public EventTask editTask(String newDescription, TaskPriority newPriority, LocalDateTime newEventDate) {
        return new EventTask(isDone, newDescription, newPriority, newEventDate);
    }

    /**
     * Changes the description of the EventTask.
     *
     * @param newDescription The new description for the Task.
     * @return New EventTask with edited description.
     */
    public EventTask editDescription(String newDescription) {
        return editTask(newDescription, taskPriority, event);
    }

    /**
     * Changes the deadline of the EventTask.
     *
     * @param newEventDate The new event datetime for the Task.
     * @return New EventTask with edited event datetime.
     */
    public EventTask editEventDate(LocalDateTime newEventDate) {
        return editTask(description, taskPriority, newEventDate);
    }

    /**
     * Changes the priority of the EventTask.
     *
     * @param newPriority The new priority for the Task.
     * @return New EventTask with edited priority.
     */
    public EventTask editPriority(TaskPriority newPriority) {
        return editTask(description, newPriority, event);
    }

    /**
     * Toggles an EventTask between done and undone.
     *
     * @return EventTask that is marked as done/undone.
     */
    public EventTask markAsDone() {
        return new EventTask(!isDone, getDescription(), taskPriority, event);
    }

    /**
     * Converts the EventTask into a string format for saving into file.
     *
     * @return EventTask in string format for file storage.
     */
    @Override
    public String saveTask() {
        return String.format("%s /at %s", super.saveTask(), event);
    }

    /**
     * Converts the EventTask into a string format for display on user output.
     *
     * @return EventTask in string format for user output.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s, %s)", super.toString(), event.toLocalDate(), event.toLocalTime());
    }
}
