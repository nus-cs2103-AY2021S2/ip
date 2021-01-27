class ListCommand extends Command{

    ListCommand() {
        super(null, null, null, null, false);
    }


    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getList());
    }
}
