public class ExitCommand extends Command{
    public ExitCommand(CommandEnum type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        snomio.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
