public class ExitCommand implements Command {
    @Override
    public String execute(TaskList taskList) {
        return DukeStrings.MESSAGE_BYE;
    }
}
