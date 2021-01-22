import java.util.LinkedList;

public class EventTask extends Task {
    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("E");
        list.addLast(at);
        return list;
    }
}
