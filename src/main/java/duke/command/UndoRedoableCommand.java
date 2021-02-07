package duke.command;
import duke.task.TaskList;

public class UndoRedoableCommand {
    private TaskList memento;

    public void setMemento(TaskList taskList) {
        memento = taskList.clone();
    }

    public TaskList getMemento() {
        return memento;
    }
}
