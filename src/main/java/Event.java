class Event extends Task {
    protected final String deadline;

    public Event(String eventInfo) {
        super((eventInfo.split("/at")[0]).substring(0,eventInfo.split("/at")[0].length() - 1));
        this.deadline = eventInfo.split("/at")[1].substring(1);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.taskName + " (at: " + deadline + ")";
        } else {
            return "[E][ ] " + this.taskName + " (at: " + deadline + ")";
        }
    }
}