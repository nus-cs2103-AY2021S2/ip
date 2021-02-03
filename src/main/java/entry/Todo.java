package entry;

public class Todo extends Task{
    /**
     * Returns a Task.Todo
     * @param description description of the object
     * **/
    public Todo(String description) {
        super(description);
    }
    public Todo(Boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * @return String representation of the object
     */
    @Override
    public String toString() {
        String type = "[T]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description;
    }

    /**
     * Format = {type}{done}{description}
     * @return String to be stored in the txt storage file.
     */
    public String toStorage(){
        //type
        String res = "T";
        //done status
        res += "\u001E" + (isDone ? "T" : "F");
        //description
        res += "\u001E" + this.description;
        return res;
    }
}