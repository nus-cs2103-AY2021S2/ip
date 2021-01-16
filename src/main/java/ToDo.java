public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
