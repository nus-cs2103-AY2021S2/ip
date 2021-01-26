public class AddCommand extends Command{
    
    String inputStr; 
    
    public AddCommand(String inputStr) { 
        this.inputStr = inputStr;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        Task task;
        if (this.inputStr.startsWith("todo")) {
            task = Todo.createTodo(this.inputStr);
        } else if (this.inputStr.startsWith("deadline")) {
            task = Deadline.createDeadline(this.inputStr);
        } else {
            //inputStr starts with event
            task = Event.createEvent(this.inputStr);
        }
        tasks.getTaskList().add(task);
        ui.printAddedSuccess(task, tasks.getTaskList().size());
        storage.save(tasks);
    }
    
}

