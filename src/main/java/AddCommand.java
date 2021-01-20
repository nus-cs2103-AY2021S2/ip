public class AddCommand implements Command {

    private final String description;

    public AddCommand(String input) {
        description = input;
    }

    public boolean shouldExit() {
        return false;
    }

    public TaskList execute(TaskList taskList) {
        taskList.addTask(new Task(description));
        return taskList;
    }

    public String getResponse() {
        return "added: " + description;
    }
}
