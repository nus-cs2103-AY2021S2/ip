public class DoneCommand extends Command {

    public DoneCommand(String[] commandSplit) {
        super(commandSplit);
    }

    @Override
    void execute(TaskList list) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandSplit[1]);
            list.done(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number to mark a task as done.");
        }
    }

}
