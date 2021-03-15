public class TodoTask extends Task {
    public TodoTask(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
