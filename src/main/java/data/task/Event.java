package data.task;

import common.TimeWrapper;

public class Event extends Task {

    protected TimeWrapper at;

    public Event(String description, TimeWrapper at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public static Event getEvent(String description, String at) {
        return new Event(description, TimeWrapper.getTimeWrapper(at), false);
    }

    @Override
    public String getContentString(){
        return String.format("E|%d|%S|%S",isDone ? 1 : 0, this.description, this.at);

    }
    @Override
    public Task markDone(){
        return new Event(description,at,true);
    }

    @Override
    public boolean isSameTime(String time) {
        return at.equals(TimeWrapper.getTimeWrapper(time));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
