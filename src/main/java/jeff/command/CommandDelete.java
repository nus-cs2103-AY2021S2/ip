package jeff.command;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;
import jeff.task.Task;

public class CommandDelete extends Command {

    public CommandDelete(String main) {
        super(main);
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        try {
            int taskIndex = Integer.parseInt(getMainInfo()) - 1;
            Task toRemove = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            return "Noted. I've removed this task:\n" + toRemove + tasks.formatNumTasks();
        } catch (NumberFormatException e) {
            throw new JeffException("indicate task number as an integer");
        } catch (IndexOutOfBoundsException e) {
            throw new JeffException("task number does not exist");
        }
    }
}
