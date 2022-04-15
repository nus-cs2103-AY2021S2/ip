package jeff.command;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;

public class CommandBye extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        storage.save(tasks.getTaskList());
        return "Bye. Hope to see you again!";
    }
}
