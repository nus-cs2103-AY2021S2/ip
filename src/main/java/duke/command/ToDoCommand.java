package duke.command;

import duke.bot.Storage;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.task.ToDo;

/** An executable command to create a basic task with description */
public class ToDoCommand extends Command {
    /** Description of a ToDo that is going to be created */
    private String desc = "";

    /**
     * Constructor of a ToDoCommand
     *
     * @param desc Description of a ToDo that is going to be created
     */
    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the ToDo command to create a ToDo in the list
     *
     * @throws DukeCommandException if there is an issue with adding the ToDo into the list or saving the ToDo into the
     * hard disk
     */
    @Override
    public String execute() throws DukeCommandException {
        try {
            ToDo toDo = taskManager.addToDo(this.desc);
            Storage.saveTasks(taskManager.getTasks());
            return ui.constructAddMsg(toDo, taskManager.getTasksSize());
        } catch (DukeException e) {
            throw new DukeCommandException("todo", desc, e.getMessage());
        }
    }
}
