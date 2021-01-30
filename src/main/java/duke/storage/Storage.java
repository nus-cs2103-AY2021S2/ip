package duke.storage;

import duke.tasklist.TaskList;

/**
 * Handles storage for Duke
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        FileDirectoryChecker.prepareFile(filePath);
    }

    /**
     * Loads all tasks from internal storage
     * @return the task list containing all the tasks from internal storage
     */
    public TaskList load() {
        return DukeFileReader.loadTasks();
    }

    /**
     * Saves the tasks in task list into internal storage
     * @param taskList the current instance of task list used by Duke
     */
    public void saveTasksToStorage(TaskList taskList) {
        DukeFileWriter.saveTaskListInInternalStorage(taskList.getTaskList(), filePath);
    }
}
