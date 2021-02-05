package duke.task;

/**
 * This is the Task class that a user can input to the chatbot
 */
public class Task {
    private final String input;
    private taskState state;

    /**
     * This is the Task constructor.
     * @param input This stores the String input for the Task.
     */
    public Task(String input) {
        this.input = input;
        this.state = taskState.NOTDONE;
    }

    /**
     * This modifies the task to be in the Task_State.DONE.
     * @return This returns the modified done Task.
     */
    public Task doTask() {
        this.state = taskState.DONE;
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
    public taskState getState() {
        return state;
    }

    /**
     * This passes the tasks to be saved to Storage in a readable manner for Storage.
     * @return This returns the modified save message that's stored on a file for saving.
     */
    public String saveTask() {
        String stateB;
        if (state == taskState.DONE) {
            stateB = "1";
        } else {
            stateB = "0";
        }
        return " | " + stateB + " | " + input;
    }

    @Override
    public String toString() {
        return "[" + state + "] " + input;
    }
}
