class Event extends Task {
    protected final String timing;

    public Event(String eventInfo) {
        super((eventInfo.split("/at")[0]).substring(0,eventInfo.split("/at")[0].length() - 1));
        this.timing = eventInfo.split("/at")[1].substring(1);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.taskName + " (at: " + timing + ")";
        } else {
            return "[E][ ] " + this.taskName + " (at: " + timing + ")";
        }
    }
}