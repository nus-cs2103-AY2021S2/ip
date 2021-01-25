package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand(String commandType) {
        super.commandType = commandType;
        super.commandDetails = "";
        super.dateTime = "";
        super.outputMessage = "";
        super.index = -1;
    }

    public void retrieveList(TaskList taskList) {
        StringBuilder currText = new StringBuilder(" Here are the tasks in your list:");

        for (int num = 1; num <= taskList.size(); num++) {
            Task currentTask = taskList.get(num - 1);
            currText.append("\n\t ").append(num).append(".").append(currentTask.toString());
        }
        this.outputMessage = currText.toString();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        retrieveList(taskList);
        ui.display(outputMessage);
    }

    @Override
    public boolean continueInput() {
        return true;
    }
}
