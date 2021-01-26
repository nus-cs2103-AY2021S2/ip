import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.store(tasks);
        ui.bye();
    }
}
