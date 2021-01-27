package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of done command.
 */
public class DoneCommand {

    /**
     * Forwards the mark task as done operation to TaskList.
     * @param index of task to mark as done
     */
    public void execute(TaskHandler taskList, int index) {
        taskList.markTaskDone(index);
    }
}
