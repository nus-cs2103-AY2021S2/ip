public class DeleteCommand extends Command {
    public DeleteCommand(String[] commandSplit) {
        super(commandSplit);
    }

    @Override
    void execute(TaskList list) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandSplit[1]);
            list.remove(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number to delete a task.");
        }
    }
}
