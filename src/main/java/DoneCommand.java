class DoneCommand extends Command{

    DoneCommand(String index) {
        super(null, index, null,null, false);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Task t = tasks.markTaskAsDone(description);
        ui.showDone(t.toString(), tasks.getSize());
        storage.save(tasks.listOutTaskInString());
    }
}
