public class EventCommand extends Command {
    EventCommand(String line) {
        super(line);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String getType() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return line;
    }
}
