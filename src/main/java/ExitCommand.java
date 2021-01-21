public class ExitCommand implements Command {
    @Override
    public String execute(TaskList taskList) {
        return DukeString.MESSAGE_BYE;
    }
}
