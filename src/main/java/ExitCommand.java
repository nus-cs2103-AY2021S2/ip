public class ExitCommand implements Command {
    @Override
    public String execute(final TaskList taskList) {
        return DukeString.MESSAGE_BYE;
    }
}
