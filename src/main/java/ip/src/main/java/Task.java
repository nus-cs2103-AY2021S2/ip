package ip.src.main.java;

/**
 * Task class that stores Task details and Task status.
 *
 */

public class Task {
    //int id;
    protected String content;
    protected boolean done = false;

    public Task(String content) {
        //this.id = id;
        this.content = content;

    }

    /**
     * Updates the Task Status to completed.
     *
     */
    public void markDone() {
        this.done = true;
    }

    public boolean isDone(String doneStatus) {
        return doneStatus.equals(" 1 ");
    }

    /**
     * Checks if Task content has matching keyword.
     *
     * @return Returns True if task content has keyword.
     */
    public boolean isMatch(String keyword) {
        return this.content.contains(keyword);
    }

    protected boolean isToDoTask(String inputType) {
        return inputType.equals("T");
    }

    protected boolean isEventTask(String inputType) {
        return inputType.equals("E");
    }

    public String toString() {
        return this.content;
    }


}


