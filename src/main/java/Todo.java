public class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T|" + super.toStorageString();
    }
}
