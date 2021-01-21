public class Event extends Task{
    protected String at;

    public Event(int number, String name, String at) {
        super(number, name);
        this.at = at;
    }

    @Override
    public void addTask(int count) {
        super.addTask(count);
    }

    @Override
    public String toString() {
        return "     [E]" + super.toString() + "(at: " + at + ")";
    }
}
