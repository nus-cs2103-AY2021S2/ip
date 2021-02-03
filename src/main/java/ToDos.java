public class ToDos extends Task {
    ToDos(String description) throws EmptyArgumentException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
