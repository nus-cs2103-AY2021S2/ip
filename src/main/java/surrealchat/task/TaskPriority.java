package surrealchat.task;

import java.util.InputMismatchException;

/**
 * Enum to set priority levels of tasks.
 */
public enum TaskPriority {
    HIGH(3), MEDIUM(2), LOW(1);

    private final int priorityLevel;

    TaskPriority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Obtains the priority level in int form.
     * @return Int of priority level.
     */
    public int getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Obtains correct priority enum based on num.
     * @param num The number denoting priority in ascending order.
     * @return The appropriate TaskPriority enum.
     */
    public static TaskPriority getPriorityType(int num) {
        switch(num) {
        case 1:
            return TaskPriority.LOW;
        case 2:
            return TaskPriority.MEDIUM;
        case 3:
            return TaskPriority.HIGH;
        default:
            throw new InputMismatchException("Num not in range [1,3]! Not stonks!\n");
        }
    }
}
