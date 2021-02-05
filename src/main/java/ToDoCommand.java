public class ToDoCommand extends Command {
    public ToDoCommand(String line) {
        super(line);
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String getDescription() {
        return line;
    }
}
