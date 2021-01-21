public class Todo extends Task{
    /**
     * Returns a Todo
     * @param description description of the todo
     * **/
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String type = "[T]";
        String doneStatus = "[" + getStatusIcon() + "]";
        return type + doneStatus + " " + this.description;
    }
}
