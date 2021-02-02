public class Event extends Task {

    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public Task setDone() {
        Event doneTask = new Event(this.name, this.at);
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String getSaveText() {
        return "E | " + super.getSaveText();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
