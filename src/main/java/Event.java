public class Event extends Task{
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public Event(String name, String at, boolean done) {
        super(name);
        this.at = at;
        this.done = done;
    }

    @Override
    public void addTask(int count) throws DukeException {
        if(this.name.equals("")) {
            throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            super.addTask(count);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

