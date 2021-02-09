public class CommandResult {
    private TaskList relatedTaskList;
    private Task relatedTask;
    private String feedbackToUser;

    public CommandResult() {}
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public CommandResult(String feedbackToUser, Task relatedTask) {
        this.feedbackToUser = feedbackToUser;
        this.relatedTask = relatedTask;
    }

    public CommandResult(String feedbackToUser, TaskList relatedTaskList) {
        this.feedbackToUser = feedbackToUser;
        this.relatedTaskList = relatedTaskList;
    }

    public Task getTask() {
        return this.relatedTask;
    }

    public TaskList getTaskList() {
        return this.relatedTaskList;
    }

    public String getFeedbackToUser(){
        return this.feedbackToUser;
    }
}
