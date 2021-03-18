/**
 * Represents todos type of tasks.
 */
public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns String format of todos
     *
     * @return String format of todos
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public void editTask(String newDescription) {
        super.editTask(newDescription);
    }

//    /**
//     * Returns String of error message for empty description
//     *
//     * @return String of error message for empty description
//     */
//    @Override
//    public static String getEmptyDescError() {
//        return "Oops! Description of todo cannot be empty";
//    }

    /**
     * Formats data for saving into text file.
     *
     * @return T | isDone | description
     */
    @Override
    public String formatData() {
        return "T | " + super.formatData();
    }
}
