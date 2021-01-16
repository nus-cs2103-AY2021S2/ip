public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return String.format("Got it. I've added this task:\n" +
                "\t [%s][%s] %s", super.getType(), super.getStatusIcon(), super.getDescription());
    }
}
