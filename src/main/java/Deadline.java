public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * toString method overriding the one in class Task
     * @return the user-friendly String representation of the Deadline item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[D][%s] %s", doneMark, name);
    }
}
