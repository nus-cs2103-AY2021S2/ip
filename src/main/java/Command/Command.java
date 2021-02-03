package Command;
import Oracle.TaskList;
import Oracle.Ui;

public interface Command {
    boolean execute(Ui ui, TaskList tasks);
}
