package Tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String getStatusString() {
        return "[T]" + super.getStatusString();
    }
}
