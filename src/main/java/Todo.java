public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.name;
        } else {
            return "[T][ ] " + this.name;
        }
    }
}
