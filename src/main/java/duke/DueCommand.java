package duke;
import java.util.ArrayList;


public class DueCommand extends Command {

    DueCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    void execute(TaskManager taskManager, Ui ui, Storage storage) {
        String date = parsedCommand[1];
        ArrayList<Task> list = taskManager.getTasksOn(date);
        ui.showDueTasks(list, date);
    }
}
