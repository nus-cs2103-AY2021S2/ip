package command;
import oracle.TaskList;
import oracle.Ui;

public interface Command {
    boolean execute(Ui ui, TaskList tasks);
}
