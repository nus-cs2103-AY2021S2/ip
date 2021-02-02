public class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] argumentsArr = this.arguments.split(" /by ", 2);
        tasks.add(new Deadline(argumentsArr[0], argumentsArr[1]));
        super.execute(tasks, ui, storage);
    }
}
