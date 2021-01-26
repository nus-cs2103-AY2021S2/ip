public class Todo extends Task {
    private static final char SYMBOL = 'T';

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s",SYMBOL, super.toString());
    }

    @Override
    public String save() {
        return String.format("%c,%s\n", SYMBOL, super.save());
    }
}
