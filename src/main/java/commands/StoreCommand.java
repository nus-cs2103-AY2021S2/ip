package commands;


import data.IDuke;
import data.task.ITask;
import data.task.TaskList;
import storage.TaskManager;


public abstract class StoreCommand extends Command {

    protected StoreCommand(int targetIndex, IDuke duke) {
        super(targetIndex, duke);
    }

    /**
     * Adds task in Duke object.
     * Also invokes storage class to store task list on disk.
     *
     * @param task The tasks to be stored.
     */
    protected void storeTask(ITask task) {
        TaskManager tm = duke.getTaskManager();
        TaskList list = duke.getTasks();
        list.add(task);
        tm.save(list.getList());
    }

}
