package project.command;

import project.common.PrintedText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.Task;
import project.task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            int id = Parser.parseIntParameter(userInput);
            Task deleted = taskList.deleteTask(id);
            this.saveTasks(taskList, storage);
            return ui.showDeleteSuccess(id, deleted, taskList.getTotalNumberOfTasks());
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showFormatError(PrintedText.DELETE_FORMAT);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ui.showInvalidIndexError();
        }
    }
}
