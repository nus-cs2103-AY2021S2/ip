public class ExitCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ExitCommand) {
            return true;
        }
        return false;
    }
}
