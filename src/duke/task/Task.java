package duke.task;

/**
 * Parent Class of all task
 */
public class Task {
    protected String description;

    private boolean isDone;

    /**
     * Default Constructor of a task
     * @param description string description of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of a task
     * @param description string description of a task
     * @param doneInt 1 indicates that the task is done, 0 indicates that the task is not done
     */

    public Task(String description, int doneInt) {
        this.description = description;
        if (doneInt == 1){
            this.isDone = true;
        }else{
            this.isDone = false;
        }
    }

    /**
     * @return If the task is done, return an X, else return a empty space character
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * @return String that describes the task and its done status
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public void changeTaskToDone(){
        isDone = true;
    }

    /**
     * @return Output string that in the format that it should be saved as
     */
    public String toSaveFormat(){
        int isDoneInt = isDone ? 1 : 0;
        return isDoneInt + "|" + description;
    }
}
