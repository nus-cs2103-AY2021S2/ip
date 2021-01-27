/** Describes the "to-do" variation of the Task class. */
public class Todo extends Task {
    /**
     * Returns a Todo object that takes in one argument
     *
     * @param n The name of the task
     */
    public Todo(String n) {
        isDone = false;
        name = n;
    }

    /**
     * Returns the string representation of the task for storage purposes.
     * @return the string representation
     */
    public String toText() {
        String d = isDone ? "+" : "-";
        return String.format("T | %1$s | %2$s", d, name);
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]")
             + " Todo: "
             + name;
    }
}
