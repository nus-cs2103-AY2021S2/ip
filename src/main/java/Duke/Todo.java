package Duke;

public class Todo extends Task {
    private String description;

    public Todo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Returns a String that has been formatted which contains the information of the To-do task.
     * String is formatted into a form to be written into the file writer.
     *
     * @return String to be passed into file writer.
     */
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

    /**
     * Returns a String that has been formatted which contains the information of the To-do task.
     * String is formatted into a form to be printed by the Ui.
     *
     * @return String to be printed by Ui.
     */
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