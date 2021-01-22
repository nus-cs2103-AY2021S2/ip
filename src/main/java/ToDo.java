import java.util.LinkedList;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("T");
        return list;
    }
}
