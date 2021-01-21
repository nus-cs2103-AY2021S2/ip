public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }
}
