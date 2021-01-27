class ExitCommand extends Command{

    ExitCommand() {
        super(null, null, null,null, true);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
