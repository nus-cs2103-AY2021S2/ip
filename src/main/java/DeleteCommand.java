public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    protected String errorMessage;
    protected String successMessage;
    private int taskNumberToDelete;

    public DeleteCommand(int taskNumber) {
        this.taskNumberToDelete = taskNumber;
    }

    @Override
    public CommandResult execute() throws DukeException {
        try {
            Task taskToDelete = tasks.deleteTask(this.taskNumberToDelete);
            successMessage = "Noted. I've removed this task:" + "\n" + taskToDelete.toString() + "\n"
                    + "Now you have " + tasks.getSize() + " tasks in the list.";
            return new CommandResult(successMessage);
        } catch (DukeException e) {
            errorMessage = e.getMessage();
            return new CommandResult(errorMessage);
        }
    }
}
