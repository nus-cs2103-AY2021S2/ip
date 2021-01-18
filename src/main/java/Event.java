public class Event extends Task {
    
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    protected Event(String name, String time, boolean completed) {
        super(name, completed);
        this.time = time;
    }

    @Override
    public Task completeTask() {
        return new Event(this.name, this.time, true);
    }

    @Override
    public String toString() {
        return super.toString().replace("[T]", "[E]") + " (at: " + this.time + ")";
    }

}
