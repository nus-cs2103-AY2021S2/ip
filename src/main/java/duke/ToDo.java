package duke;
public class ToDo extends Task {

    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String getFormattedData() {
        return  "T | " + super.getFormattedData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
