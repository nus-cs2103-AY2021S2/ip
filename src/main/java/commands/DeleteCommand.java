package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;
import tasklist.TaskList;

public class DeleteCommand extends CommandWithParameters {
    public DeleteCommand(String commandBody) {
        super("delete", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {

            handleNoArgs();

            handleInvalidOnEmptyList(taskList.isEmpty());

            int userInputIdx = Integer.parseInt(this.commandBody);

            handleInvalidListIdx(taskList.size(), userInputIdx);

            this.commandOutputMsg = taskList.deleteTask(userInputIdx);

        } catch (NumberFormatException e) {
            handleException(new InvalidArgumentException("Please enter a valid task number to delete."));
        } catch (InvalidArgumentException | MissingArgumentException e) {
            handleException(e);
        }

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
