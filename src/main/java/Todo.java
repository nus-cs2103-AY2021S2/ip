public class Todo extends Task{
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
