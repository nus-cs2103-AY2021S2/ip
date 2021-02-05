public class DeadlineCommand extends Command {

    DeadlineCommand(String line) {
        super(line);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String getDescription() {
        return line;
    }
}
