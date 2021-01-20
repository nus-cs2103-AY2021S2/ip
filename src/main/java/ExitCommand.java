public class ExitCommand implements Command {
    @Override
    public String execute() {
        return Messages.GOODBYE;
    }
}
