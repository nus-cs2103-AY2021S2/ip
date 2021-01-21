public class DeleteCommand implements Command {

    private int deletedID;
    private Task deletedTask;
    private int numTasks;

    public DeleteCommand(int deletedID) {
        this.deletedID = deletedID;
    }

    public boolean shouldExit() {
        return false;
    }

    public String getResponse() {
        return "Noted. I've removed this task:\n  " + deletedTask
        + "\nNow you have " + numTasks + " tasks in the list.\n";
    }

    public TaskList execute(TaskList taskList) {
        deletedTask = taskList.getTask(deletedID);
        taskList.removeTask(deletedID);
        numTasks = taskList.getSize();
        return taskList;
    }
    
}
