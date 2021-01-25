package duke.commands;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private static final String DONE_TASK_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String INVALID_INDEX_MESSAGE = "Please enter a valid index!";

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            taskList.completeTask(index);
            return new CommandResult(DONE_TASK_MESSAGE + taskList.getTask(index).toString(), taskList);
        } catch (IndexOutOfBoundsException ex) {
            return new CommandResult(INVALID_INDEX_MESSAGE);
        }
    }
}
