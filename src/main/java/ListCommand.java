public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getType() {
        return "list";
    }

    @Override
    public String getDescription() {
        return null;
    }
}

