package duke.command;
import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;
import duke.tasktype.Todo;
import duke.tasktype.Task;
import java.util.List;

public class CTodo implements ICommand {
    private String input;
    public CTodo(String command){
        this.input = command;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        String name = input.substring(4);
        Task todo = new Todo(name.trim());
        taskList.add(todo);
        storage.addText(todo.convertToFile());
        return ui.showTaskAdded(todo.getStatus(), taskList);
    }
}
