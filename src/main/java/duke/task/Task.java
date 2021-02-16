package duke.task;

import java.time.LocalDateTime;

/**
 * This is the Task class that a user can input to the chatbot
 */
public class Task {
    private final String input;
    private TaskState state;
    private Reminder reminder;

    /**
     * This is the Task constructor.
     * @param input This stores the String input for the Task.
     */
    public Task(String input) {
        this.input = input;
        this.state = TaskState.NOTDONE;
        this.reminder = null;
    }

    /**
     * This modifies the task to be in the Task_State.DONE.
     * @return This returns the modified done Task.
     */
    public Task doTask() {
        this.state = TaskState.DONE;
        return this;
    }

    /**
     * This gets the input stored in the Task.
     * @return This returns the input String stored.
     */
    public String getInput() {
        return input;
    }

    /**
     * This returns the current state of the Task.
     * @return This returns Task_State of the task.
     */
    public TaskState getState() {
        return state;
    }

    public void addReminder(LocalDateTime localDateTime) {
        this.reminder = new Reminder(localDateTime);
    }

    public LocalDateTime getReminderDate() {
        if (this.reminder == null) {
            return null;
        } else {
            return this.reminder.getDate();
        }
    }

    public Reminder getReminder() {
        return reminder;
    }

    /**
     * This passes the tasks to be saved to Storage in a readable manner for Storage.
     * @return This returns the modified save message that's stored on a file for saving.
     */
    public String saveTask() {
        String stateB;
        String reminderDate;
        if (state == TaskState.DONE) {
            stateB = "1";
        } else {
            stateB = "0";
        }
        if (reminder == null) {
            reminderDate = "0";
        } else {
            reminderDate = reminder.getReminderDateOnly();
        }
        return " | " + stateB + " | " + reminderDate + " | " + input;
    }

    public String printWithReminder() {
        return this.reminder.toString() + "   " + toString();
    }

    @Override
    public String toString() {
        return "[" + state + "] " + input;
    }
}
