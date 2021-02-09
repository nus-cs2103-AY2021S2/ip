import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = taskList.findTasks(description);
        ui.showMatchingTasks(matchingTasks);
    }
}
