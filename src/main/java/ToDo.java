public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc, false);
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String getTypeSymbol() {
        return "T";
    }
}
