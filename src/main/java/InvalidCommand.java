public class InvalidCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getType() {
        return "invalid";
    }

    @Override
    public String getDescription() {
        return null;
    }
}
