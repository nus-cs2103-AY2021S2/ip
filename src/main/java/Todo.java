/**
 * Represents a Task that is yet to do.
 */
public class Todo extends Task {
    public Todo(String str) {
        super(str);
        if(str.length()==0) {
            throw new IllegalArgumentException();
        }
    }

    public Todo(String str, boolean isDone) {
        super(str, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
