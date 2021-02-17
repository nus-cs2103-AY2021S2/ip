package flamingo;

import java.util.ArrayList;

public class ArchivedTaskList extends TaskList {
    protected static int numArchivedTasks;
    protected static ArrayList<Task> archivedTasks;

    /**
     * Creates a task list given existing ArrayList of archived tasks.
     * @param tasks ArrayList of archived tasks.
     */
    public ArchivedTaskList(ArrayList<Task> tasks) {
        this.archivedTasks = tasks;
        numArchivedTasks = tasks.size();
    }

    /**
     * Creates a new empty Archived Task List.
     */
    public ArchivedTaskList() {
        this.archivedTasks = new ArrayList<>();
        numArchivedTasks = 0;
    }

    /**
     * Prints out the tasks in the ArrayList.
     */
    public String listArchivedTasks() {
        String list = "Here is the archive:\n";

        for (int i = 1; i < numArchivedTasks + 1; i++) {
            list += (i + ". " + archivedTasks.get(i - 1).toString() + "\n");
        }

        return list;
    }

    /**
     * Archives a task.
     *
     * @param task Task from user input.
     * @return String confirming task has been archived.
     */
    public static String addTaskToArchive(Task task) {
        archivedTasks.add(numArchivedTasks, task);
        numArchivedTasks++;
        return Ui.addArchivedTask(task, numArchivedTasks);
    }

    /**
     * Archives all tasks in the list.
     *
     * @param tasks ArrayList of tasks to be archived.
     * @return String confirming all tasks have been archived.
     */
    public static String addAllTasksToArchive(ArrayList<Task> tasks) {
        for (Task t : tasks) {
            archivedTasks.add(numArchivedTasks, t);
            numArchivedTasks++;
        }
        return Ui.allTasksArchived(numArchivedTasks);
    }

    /**
     * Removes a task from the archive and adds it back to the todo list.
     *
     * @param taskNumber Task Number of the task.
     * @return String confirming that the task has been unarchived.
     */
    public String unarchiveTask(int taskNumber) {
        String taskString = getTaskString(taskNumber);
        addTaskToList(taskNumber);
        removeTaskFromArchive(taskNumber);
        return Ui.unarchiveTask(taskString, numArchivedTasks);
    }

    private void addTaskToList(int taskNumber) {
        Task taskToUnarchive = archivedTasks.get(taskNumber - 1);
        TaskList.tasks.add(TaskList.getNumTasks(), taskToUnarchive);
        TaskList.numTasks++;
    }

    private void removeTaskFromArchive(int taskNumber) {
        archivedTasks.remove(taskNumber - 1);
        numArchivedTasks--;
    }

    private String getTaskString(int taskNumber) {
        return archivedTasks.get(taskNumber - 1).toString();
    }
}
