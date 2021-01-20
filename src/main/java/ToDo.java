public class ToDo extends Task {

    public ToDo(String i) {
        super(i);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
