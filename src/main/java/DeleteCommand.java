class DeleteCommand extends Command{

    DeleteCommand(String index) {
        super(null, index, null,null, false);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Task t = tasks.delete(description);
        ui.showDelete(t.toString(), tasks.getSize());
        storage.save(tasks.listOutTaskInString());
    }
}
