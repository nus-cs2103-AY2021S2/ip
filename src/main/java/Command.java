public abstract class Command {
    protected TaskList taskList;
    
    void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    
    public abstract CommandResult execute();
}