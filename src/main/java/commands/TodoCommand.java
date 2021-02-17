package commands;

import exceptions.MissingArgumentException;
import tasklist.TaskList;
import tasks.Todo;

public class TodoCommand extends CommandWithParameters {
    public TodoCommand(String commandBody) {
        super("todo", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleNoArgs();

            this.commandOutputMsg = taskList.addTask(new Todo(commandBody));

        } catch (MissingArgumentException e) {
            handleException(e);
        }

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
