package duke;

import java.io.IOException;
import java.util.Stack;

import duke.command.Command;
import duke.command.RedoCommand;
import duke.command.UndoCommand;
import duke.command.UndoRedoableCommand;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private int undoRedoPointer = -1;
    private Stack<UndoRedoableCommand> commandStack = new Stack<>();

    /**
     * Creates a Duke instance with filePath to where data is stored.
     *
     * @param filePath
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            System.out.println(e.toString());
            taskList = new TaskList();
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        if (c instanceof UndoCommand) {
            undo();
        } else if (c instanceof RedoCommand) {
            redo();
        } else if (c instanceof UndoRedoableCommand) {
            deleteElementsAfterPointer();
            UndoRedoableCommand undoRedoableC = (UndoRedoableCommand) c;
            undoRedoableC.setMemento(taskList);
            commandStack.push(undoRedoableC);
            undoRedoPointer++;
        }
        return c.execute(taskList, storage);
    }

    /**
     * Clears the commands after the current pointer in the stack.
     */
    public void deleteElementsAfterPointer() {
        if (commandStack.size() < 1) {
            return;
        }
        for (int i = commandStack.size() - 1; i > undoRedoPointer; i--) {
            commandStack.remove(i);
        }
    }

    /**
     * Undoes the effect of the previous command on taskList.
     *
     * @throws DukeException
     */
    public void undo() throws DukeException {
        UndoRedoableCommand command = commandStack.get(undoRedoPointer);
        taskList = command.getMemento();
        undoRedoPointer--;
        storage.write(taskList.toDataString());
    }

    /**
     * Redoes the effect of the previous command on taskList.
     *
     * @throws DukeException
     */
    public void redo() throws DukeException {
        if (undoRedoPointer == commandStack.size() - 1) {
            return;
        }
        undoRedoPointer++;
        Command command = (Command) commandStack.get(undoRedoPointer);
        command.execute(taskList, storage);
        storage.write(taskList.toDataString());
    }
}
