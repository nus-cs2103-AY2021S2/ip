package Command;
import Oracle.TaskList;
import Oracle.Ui;

public class EmptyCommand implements Command{
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        return true;
    }
}
