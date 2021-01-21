public class DoneCommand implements Command {

    private final int id;
    private String response;

    public DoneCommand(int id) {
        this.id = id;
    }

    public boolean shouldExit() {
        return false;
    }

    public String getResponse() {
        return "Nice! I've marked this task as done:\n  " + response;
    }

    public TaskList execute(TaskList taskList) {
        taskList.markDone(id);
        response = taskList.getTask(id).toString();
        return taskList;
    }
    
}
