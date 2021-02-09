public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNumberToDelete;
    protected String successMessage;
    protected String errorMessage;

    public DeleteCommand(int taskNumber) {
        this.taskNumberToDelete = taskNumber;
    }

    public int getTaskNumberToDelete() {
        return this.taskNumberToDelete;
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
