public class ToDos extends Task{
    public ToDos(String description) throws EmptyArgument {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    String toFileString() {
        return "T," + super.toBaseFileString();
    }
}
