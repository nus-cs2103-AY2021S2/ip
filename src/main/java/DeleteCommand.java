public class DeleteCommand extends Command{
    
    String inputStr; 
    
    public DeleteCommand(String inputStr) { 
        this.inputStr = inputStr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        Task task = checkCommandIndex(tasks, "delete", this.inputStr);
        tasks.getTaskList().remove(task);
        ui.printDeleteSuccess(task, tasks.getTaskList().size());
        storage.save(tasks);
    }
    
}
