public class ToDo extends Task{
    public ToDo(int number, String name) {
        super(number, name);
    }

    @Override
    public void addTask(int count) {
        super.addTask(count);
    }

    @Override
    public String toString() {
        return "     [T]" + super.toString();
    }
}
