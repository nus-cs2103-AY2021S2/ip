public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s", getType(), isDone, getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), getStatusIcon(), getDescription());
    }
}
