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

    /**
     *Dummy method for editing the content of the old task.
     * Exact implementations in each child class, utilizing Polymorphism when editContent is called.
     *
     * @param newContent New content given by user.
     * @return A clone of the old task with the new content change.
     */
    protected Task editContent (String newContent) {
        Task newTask = new Task(newContent);
        newTask.done = this.done;
        return newTask;
    }

    /**
     * Dummy method for editDate in Task.
     * Exact implementations in each child class, utilizing Polymorphism when editDate is called.
     *
     * @param newDate New date given by user.
     */
    protected Task editDate (String newDate) {
        return this;
    }


    public String toString() {
        return this.content;
    }


}


