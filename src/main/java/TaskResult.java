import java.util.ArrayList;

public class TaskResult {
    private TaskList taskList;
    private Task task;
    private String feedbackToUser;

    public TaskResult() { }

    public TaskResult(TaskList taskList, String feedbackToUser) {
        this.taskList = taskList;
        this.feedbackToUser = feedbackToUser;
    }

    public TaskResult(Task task, String feedbackToUser) {
        this.task = task;
        this.feedbackToUser = feedbackToUser;
    }

    public Task getTask() {
        return this.task;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String getFeedbackToUser(){
        return this.feedbackToUser;
    }


}
