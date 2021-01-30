package duke.storage;

import duke.tasklist.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        FileDirectoryChecker.prepareFile(filePath);
    }

    /**
     * Loads the task list in the hard drive into duke
     */
    public TaskList load() {
        return DukeFileReader.loadTasks();
    }

    /**
     * Saves the tasks in task list into internal storage
     * @param taskList
     */
    public void saveTasksToStorage(TaskList taskList) {
        DukeFileWriter.saveTaskListInInternalStorage(taskList.getTaskList(), filePath);
    }
}
