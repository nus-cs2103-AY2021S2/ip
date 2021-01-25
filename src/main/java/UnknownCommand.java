public class UnknownCommand extends Command {
    public UnknownCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println("       OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() { return false; }
}