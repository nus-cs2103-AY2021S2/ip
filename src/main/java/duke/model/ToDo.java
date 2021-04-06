package duke.model;

import java.time.LocalDate;

/**
 * A ToDo class denotes a to-do item.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo.
     * @param isCompleted   Checked if the task is completed.
     * @param taskName     The task name.
     */
    public ToDo(Boolean isCompleted, String taskName) {
        super('T', isCompleted, taskName);
    }

    /**
     * Check if the given date equals to the task date time.
     * @param date   A local date.
     * @return       False.
     */
    @Override
    public boolean checkEqualDate(LocalDate date) {
        return false;
    }

    /**
     * A string representation of a to-do item.
     * @return  A string representing a to-do item.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
