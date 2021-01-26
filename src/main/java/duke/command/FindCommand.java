package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    public FindCommand(String commandType, String commandDetails) {
        super.commandType = commandType;
        super.commandDetails = commandDetails;
        super.dateTime = "";
        super.outputMessage = "";
        super.index = -1;
    }

    private void retrieveMatchingTasks(TaskList taskList) {
        StringBuilder currText = new StringBuilder(" Here are the matching tasks in your list:");
        int index = 0;

        for (int num = 1; num <= taskList.size(); num++) {
            Task currentTask = taskList.get(num - 1);
            String description = currentTask.getDescription();
            if(ignoreCase(description, this.commandDetails)) {
                index++;
                currText.append("\n\t ").append(index).append(".").append(currentTask.toString());
            }
        }
        this.outputMessage = currText.toString();
    }

    private boolean ignoreCase(String string, String subString) {
        return string.toLowerCase().contains(subString.toLowerCase());
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        retrieveMatchingTasks(taskList);
        ui.display(outputMessage);
    }

    @Override
    public boolean continueInput() {
        return true;
    }
}
