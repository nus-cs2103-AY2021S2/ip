package duke.storage;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        FileDirectoryChecker.prepareFile(filePath);
    }

    /**
     * Loads the task list in the hard drive into duke
     */
    public void load(TaskList taskList) {
        DukeFileReader.loadTasksIntoTaskList(taskList.getTaskList());
    }

    /**
     * Saves the tasks in task list into internal storage
     * @param taskList
     */
    public void saveTasksToStorage(TaskList taskList) {
        DukeFileWriter.saveTaskListInInternalStorage(taskList.getTaskList(), filePath);
    }
}
