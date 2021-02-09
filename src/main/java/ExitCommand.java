/**
 * exit command class
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getType() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return null;
    }
}