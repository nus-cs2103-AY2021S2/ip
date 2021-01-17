public class Event extends Task {
    private String period;

    public Event(String name, String period) {
        super(name);
        this.period = period;
    }

    /**
     * toString method overriding the one in class Task
     * @return the user-friendly String representation of the Event item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[E][%s] %s", doneMark, name);
    }
}