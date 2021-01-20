public class Event extends Task {
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public Event(String input) throws DukeEventException {
        super(input.split(" /at ", 2)[0]);

        String[] split = input.split(" /at ", 2);
        if (split.length != 2) {
            throw new DukeEventException();
        }

        this.at = split[1];
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
