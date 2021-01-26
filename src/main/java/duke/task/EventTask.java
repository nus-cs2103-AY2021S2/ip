package duke.task;

import duke.common.DukeString;

public class EventTask extends Task {
    private final String event;

    public EventTask(final String desc, final String date) {
        super(desc);
        this.event = date;
    }

    private EventTask(boolean done, String desc, String date) {
        super(desc);
        super.isDone = done;
        this.event = date;
    }

    @Override
    public String serialise() {
        return String.format(
                "%s\255%b\255%s\255%s",
                DukeString.COMMAND_EVENT,
                super.isDone,
                super.description,
                event
        );
    }

    public static EventTask deserialise(String input) {
        String[] tokens = input.split("\255");
        return new EventTask(tokens[1].equals("true"), tokens[2], tokens[3]);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), event);
    }
}
