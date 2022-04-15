package duke.command;

import duke.exception.DukeStorageException;

import duke.task.Task;

import duke.util.COMMAND;
import duke.util.Cache;
import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

import javafx.util.Pair;

/**
 * Class representing the Undo command in Duke.
 */
public class UndoCommand extends Command {
    /**
     * Executes the Undo command.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Updates the tasks.txt file after executing commands modifying TaskList/tasks.
     * @return Duke's response after executing the Undo command.
     */
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

    /**
     * Removes a task from the list.
     *
     * @param taskIdx The index of the task.
     * @param tasks The list of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @return Duke's response after removing a task from the list.
     */
    private String undoAdd(int taskIdx, TaskList tasks, MessageFormatter messageFormatter) {
        Task removedTask = tasks.getTask(taskIdx);
        tasks.deleteTask(taskIdx);
        return messageFormatter.formatUndoAdd(removedTask, tasks);
    }

    /**
     * Adds a task back into the list.
     *
     * @param taskIdx The index of the task.
     * @param task The task to be added back into the list.
     * @param tasks The list of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @return Duke's response after adding a task back into the list.
     */
    private String undoDelete(int taskIdx, Task task, TaskList tasks, MessageFormatter messageFormatter) {
        tasks.addTask(task, taskIdx);
        return messageFormatter.formatUndoDelete(task, tasks);
    }

    /**
     * Sets a task as not done.
     *
     * @param task The task to be set as not done.
     * @param messageFormatter Formats Duke's response into a String.
     * @return Duke's response after setting a task as not done.
     */
    private String undoDone(Task task, MessageFormatter messageFormatter) {
        task.setNotDone();
        return messageFormatter.formatUndoDone(task);
    }
}
