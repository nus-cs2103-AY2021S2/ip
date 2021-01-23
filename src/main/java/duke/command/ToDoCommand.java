package duke.command;

import duke.Storage;
import duke.task.ToDo;
import duke.exception.DukeException;
import duke.exception.DukeCommandException;

public class ToDoCommand extends Command {
    private String desc = "";

    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            ToDo toDo = taskManager.addToDo(this.desc);
            ui.printAddMsg(toDo, taskManager.getTasksSize());
            Storage.saveTasks(taskManager.getTasks());
        } catch(DukeException e) {
            throw new DukeCommandException("todo", desc, e.getMessage());
        }
    }
}
