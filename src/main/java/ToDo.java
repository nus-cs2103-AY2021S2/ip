public class ToDo extends Task {
    public ToDo(String description, int id) {
        super(description, id);
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
