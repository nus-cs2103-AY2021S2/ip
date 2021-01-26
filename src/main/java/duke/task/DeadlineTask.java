package duke.task;

import duke.common.DukeString;

public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(final String desc, final String date) {
        super(desc);
        this.deadline = date;
    }

    private DeadlineTask(boolean done, String desc, String date) {
        super(desc);
        super.isDone = done;
        this.deadline = date;
    }

    @Override
    public String serialise() {
        return String.format(
                "%s\255%b\255%s\255%s",
                DukeString.COMMAND_DEADLINE,
                super.isDone,
                super.description,
                deadline
        );
    }

    public static DeadlineTask deserialise(String input) {
        String[] tokens = input.split("\255");
        return new DeadlineTask(tokens[1].equals("true"), tokens[2], tokens[3]);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
