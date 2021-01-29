public class ToDos extends Task {

    public ToDos(boolean isDone, String eventName) {
        super(isDone, eventName, "T");
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
