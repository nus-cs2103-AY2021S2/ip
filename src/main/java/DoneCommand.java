public class DoneCommand implements Command {

    private final int id;
    private String response;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return "Nice! I've marked this task as done:\n" + response;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        taskList.markDone(id);
        response = taskList.getTask(id).toString();
        return taskList;
    }
    
}
