public class DeadlineCommand extends Command {

    private Deadline deadline;

    private static final String NO_ARGUMENT_ERROR = "Please specify a deadline!";

    public DeadlineCommand(String description) throws DukeException {
        try {
            if (description.isBlank()) {
                throw new DukeException(NO_ARGUMENT_ERROR);
            }
            String[] descArr = description.split(" /by ");
            if (descArr.length < 2) {
                throw new DukeException(NO_ARGUMENT_ERROR);
            }
            this.deadline = new Deadline(descArr[0], descArr[1]);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.deadline);
        ui.printAddTaskAck(this.deadline, tasks);
        storage.write(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
