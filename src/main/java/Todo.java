public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean done) {
        super(name, done);
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
