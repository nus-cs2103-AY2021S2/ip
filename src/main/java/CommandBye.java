public class CommandBye extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO INITIATE SHUTDOWN IN ALL ARGUMENTS
        ui.printCommand(this);
    }

    @Override
    public String toDukeOutput() {
        return "I see you're done for now boss, see you soon!";
    }
}
