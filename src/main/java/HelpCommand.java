public class HelpCommand extends Command {
    static final String COMMAND_WORD = "help";

    public HelpCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public void execute() {
        this.ui.showHelp();
    }
}
