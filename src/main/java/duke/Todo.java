package duke;

public class Todo extends Task {
    private String description;

    public Todo(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String formatToSave() {
        String str = "T | ";
        if (isDone) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description;
        return str;
    }

    @Override
    public String toString() {
        String str = "[T]";
        if (isDone) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description;
        return str;
    }
}