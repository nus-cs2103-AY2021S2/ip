package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String serialise() {
        String type = "TODO";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|').append(description);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
