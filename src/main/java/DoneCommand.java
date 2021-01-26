public class DoneCommand extends Command {

    String inputStr;
    
    public DoneCommand(String inputStr) { 
        this.inputStr = inputStr; 
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        Task task = checkCommandIndex(tasks, "done", this.inputStr);
        if (task.getDone()) {
            throw new ChatException(String.format("Task already completed\n%s", task));
        } else {
            task.completed();
            ui.printWellDone(task);
            storage.save(tasks);
        }
    }
    
}
