public class UnknownCommand extends Command {
    public UnknownCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.unknownCommandInteraction();
    }

    public boolean isExit() {
        return false;
    }
}
