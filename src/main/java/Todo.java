/*
 * A type of Task.
 */
public class Todo extends Task {
    public Todo(String description, String date, String time) {
        super(description, date, time, "[T]", true);
    }
}