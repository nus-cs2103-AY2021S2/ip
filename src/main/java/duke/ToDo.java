package duke;

/**
 * Todo implementation of the super class task. Includes method to properly
 * format the printing of todo tasks.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    @Override
    public String saveStatus() {
        return "T" + super.saveStatus();
    }
}
