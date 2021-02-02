public class Todo extends Task{
    /**
     * Returns a Todo
     * @param description description of the todo
     * **/
    public Todo(String description) {
        super(description);
    }
    public Todo(Boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        String type = "[T]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description;
    }

    /***
     * Format = {type}{done}{description}
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