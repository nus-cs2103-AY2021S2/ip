package Entry;

public class Task {
    /** base class for the Task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @param isDone boolean to indicate if the task is done
     * @param description description of the task
     */
    public Task(Boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * @return [X] or [ ]
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); //return X symbol or empty string
    }

    public void markDone() {
        this.isDone = !this.isDone;
    }

    String description;
    boolean isDone;

    @Override
    public String toString() {
        String doneStatus = "[" + getStatusIcon() + "]";
        return doneStatus + " " + this.description;
    }


    /***
     * Format = {done}{description}
     */
    public String toStorage(){
        //type N for 'none'
        String res = "N";
        //done status
        res += "\u001E" + (isDone ? "T" : "F");
        //description
        res += "\u001E" + this.description;
        return res;
    }
}
