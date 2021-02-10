package duke.command;

import duke.exception.DukeStorageException;

import duke.task.Task;
import duke.util.COMMAND;
import duke.util.Cache;
import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

import javafx.util.Pair;

public class UndoCommand extends Command {
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        try {
            if (Cache.isEmpty()) {
                return "No actions to undo!";
            }
            Pair<Pair<Task, Integer>, String> p = Cache.pop();
            Pair<Task, Integer> taskIdxPair = p.getKey();
            Task task = taskIdxPair.getKey();
            int taskIdx = taskIdxPair.getValue();
            String command = p.getValue();
            String undoMsg;
            if (command.equals(COMMAND.TODO) || command.equals(COMMAND.DEADLINE) || command.equals(COMMAND.EVENT)) {
                undoMsg = undoAdd(taskIdx, tasks, messageFormatter);
            } else if (command.equals(COMMAND.DELETE)) {
                undoMsg = undoDelete(taskIdx, task, tasks, messageFormatter);
            } else if (command.equals(COMMAND.DONE)) {
                undoMsg = undoDone(task, messageFormatter);
            } else {
                undoMsg = "Unknown command type, aborting...";
            }
            storage.storeData(tasks);
            return undoMsg;
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }

    private String undoAdd(int taskIdx, TaskList tasks, MessageFormatter messageFormatter) {
        Task removedTask = tasks.getTask(taskIdx);
        tasks.deleteTask(taskIdx);
        return messageFormatter.formatUndoAdd(removedTask, tasks);
    }

    private String undoDelete(int taskIdx, Task task, TaskList tasks, MessageFormatter messageFormatter) {
        tasks.addTask(task, taskIdx);
        return messageFormatter.formatUndoDelete(task, tasks);
    }

    private String undoDone(Task task, MessageFormatter messageFormatter) {
        task.setNotDone();
        return messageFormatter.formatUndoDone(task);
    }
}
