package duke.commands;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private static final String MESSAGE_DONE_TASK = "Nice! I've marked this task as done:\n";
    private static final String MESSAGE_INVALID_INDEX = "Please enter a valid index!";

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            taskList.completeTask(index);
            return new CommandResult(MESSAGE_DONE_TASK + taskList.getTask(index).toString(), taskList);
        } catch (IndexOutOfBoundsException ex) {
            return new CommandResult(MESSAGE_INVALID_INDEX);
        }
    }
}
