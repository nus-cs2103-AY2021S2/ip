public class CommandResult {
    private String messageForUser;
    private TaskList updatedTaskList;

    public CommandResult(String messageForUser) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = null;
    }
    
    public CommandResult(String messageForUser, TaskList updatedTaskList) {
        this.messageForUser = messageForUser;
        this.updatedTaskList = updatedTaskList;
    }
    
    public String getMessageForUser() {
        return messageForUser;
    }

    public TaskList getUpdatedTaskList() {
        return updatedTaskList;
    }
}
