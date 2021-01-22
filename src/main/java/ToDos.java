public class ToDos extends Task {

    public ToDos(String eventName) {
        super(false, eventName);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
