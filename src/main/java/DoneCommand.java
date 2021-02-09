public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private int taskNumberToDone;
    private String successMessage;
    private String errorMessage;

    public DoneCommand(int taskNumber) {
        this.taskNumberToDone = taskNumber;
    }

    public int getTaskNumberToDone() {
        return this.taskNumberToDone;
    }

    @Override
    public CommandResult execute() throws DukeException {
        try {
            Task taskToDone = tasks.markTaskAsDone(this.taskNumberToDone);
            successMessage = "Nice! I've marked this task as done:" + "\n" + taskToDone.toString();
            return new CommandResult(successMessage);
        } catch (DukeException e) {
            errorMessage = e.getMessage();
            return new CommandResult(errorMessage);
        }
    }
}
