package project.command;

import java.util.Arrays;

import project.common.PrintedText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public class DoneCommand extends Command {
    public DoneCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            int id = Parser.parseIntParameter(userInput);
            taskList.markTaskAsDone(id);

            // will save in every storage path provided
            Arrays.stream(storage).forEach(s -> {
                s.saveData(taskList);
                assert s.isSaved();
            });

            return ui.showDoneSuccess(id, taskList.getTask(id), taskList.getTotalNumberOfTasksUndone());
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showFormatError(PrintedText.DONE_FORMAT);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ui.showInvalidIndexError();
        }
    }
}
