public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
