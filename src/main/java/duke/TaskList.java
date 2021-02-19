package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a container of multiple tasks
 */
public class TaskList {

    protected List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks contained in the task list
     *
     * @return int of the number of tasks
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Returns the task specified from the task list
     *
     * @param i the task index of the task to be extracted
     * @return the Task to be extracted
     */
    public Task getTask(int i) {
        return this.taskList.get(i - 1);
    }

    /**
     * Returns the task list
     *
     * @return the task list
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * adds a specified task to the task list
     *
     * @param task the task that is to be added to the task list
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * deletes a specified task from the task list
     *
     * @param taskIndex the index of the task that is to be removed from the task list
     */
    public void deleteTask(int taskIndex) {
        this.taskList.remove(taskIndex - 1);
    }

    /**
     * given info related to the updating of a task, updates the task from the task list
     *
     * @param updateInfo relevant information related to the update
     */
    public void updateTask(String[] updateInfo) throws DukeException {
        int taskIndex = Integer.parseInt(updateInfo[1]);
        String type = updateInfo[2];
        String info = updateInfo[3];
        Task targetTask = this.taskList.get(taskIndex - 1);
        if (type.equals("time")) {
            targetTask.updateTime(info);
        } else if (type.equals("desc")) {
            targetTask.updateDesc(info);
        } else {
            throw new DukeException("Please enter your input in the correct format");
        }
    }

    /**
     * Returns a boolean value of whether the requested task exists in the task list
     *
     * @param index the task index of the task requested
     * @return the boolean of whether the task exists
     */
    public boolean checkTaskExist(int index) {
        if (index < 1 || index > this.taskList.size()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * check if the task list is empty
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * prints out all tasks to the console in the format
     */
    public void printTask() {
        int i = 1;
        for (Task t : this.taskList) {
            System.out.println("Task loaded: " + i + ". " + t.toString());
            i++;
        }
    }

    /**
     * Returns a List<Task> consisting of tasks which contain the keyword
     * If nothing is found, an empty List is returned
     *
     * @param keyword the keyword we aim to find.
     * @return List<Task></> of found tasks
     */
    public List<Task> findTask(String keyword) {
        List<Task> foundTasks = new ArrayList<>();

        for (Task t : this.taskList) {
            String taskDesc = t.getDescription();
            if (taskDesc.contains(keyword)) {
                foundTasks.add(t);
            }
        }

        return foundTasks;
    }

}
