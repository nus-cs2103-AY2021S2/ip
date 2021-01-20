public class AddCommand implements Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList) {
        taskList.addTask(input);
        return "added: " + input;
    }
}
