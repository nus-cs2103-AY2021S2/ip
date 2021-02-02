/*
Task is the general case for Todo, Deadline, and Event. In future we might change this to interface or smt,
I dont know... depends on future requirements.
 */
public class Task {
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(Boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

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
