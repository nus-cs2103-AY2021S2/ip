package soonwee.duke;

/**
 * Represents a Task instance.
 */
public class Task {

    public String taskDesc;
    public boolean isCompleted; //Task 1.3

    /**
     * Instantiate a task with its description.
     *
     * @param taskDesc task description.
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isCompleted = false;
    }

    /**
     * Set a task as completed.
     */
    public void setCompleted(){
        this.isCompleted = true;
    }

    @Override
    public String toString(){
        String marked = new String();
        if (this.isCompleted) {
            marked = "X";
        } else {
            marked = " ";
        }
        return "[" + marked + "] " + this.taskDesc;
    }
}
