package duke.task;

import duke.common.DukeString;

public class TodoTask extends Task {
    public TodoTask(final String desc) {
        super(desc);
    }

    private TodoTask(boolean done, String desc) {
        super(desc);
        super.isDone = done;
    }

    @Override
    public String serialise() {
        return String.format(
                "%s\255%b\255%s",
                DukeString.COMMAND_EVENT,
                super.isDone,
                super.description
        );
    }

    public static TodoTask deserialise(String input) {
        String[] tokens = input.split("\255");
        return new TodoTask(tokens[1].equals("true"), tokens[2]);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
