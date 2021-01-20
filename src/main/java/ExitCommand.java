public class ExitCommand implements Command {
    @Override
    public String execute() {
        return DukeStrings.MESSAGE_BYE;
    }
}
