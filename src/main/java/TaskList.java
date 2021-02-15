import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> myTasks) {
        this.taskList = myTasks;
    }

    /**
     * Return the size of a TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Return the list of tasks in a TaskList.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Add task into a TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Delete task by its order number from a TaskList.
     *
     * @param taskNumber Order number of the task to be deleted.
     * @return Task deleted.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task curTask = this.taskList.remove(taskNumber - 1);
            return curTask;
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!! The task number is invalid.");
        }
    }

    /**
     * Mark a task in a TaskList as done.
     *
     * @param taskNumber Order number of the task to be completed.
     * @return Task marked as done.
     */
    public Task markTaskAsDone(int taskNumber) throws DukeException {
        try {
            Task curTask = this.taskList.get(taskNumber - 1);
            curTask.markAsDone();
            return curTask;
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!! The task number is invalid.");
        }
    }

    /**
     * Find tasks with description that contains a keyword specified by user.
     *
     * @param keyword Keyword to look for in tasks' description.
     * @return List of tasks that have the keyword in their description.
     */
    public TaskList findMatchingTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<> ();
        // loop through every task in the list
        for (Task t: this.taskList) {
            // add task to list if its description contains the keyword
            if (t.getDescription().contains(keyword)){
                matchingTasks.add(t);
            }
        }
//        int totalTask = matchingTasks.size();
        return new TaskList(matchingTasks);
    }

    /**
     * View all tasks scheduled on a specific date.
     * @param targetDate The target date on which the schedule is viewed.
     * @return List of all tasks schedule on the date.
     */
    public ArrayList<Task> getScheduleByDate(LocalDate targetDate) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        // loop through every task in the list
        for (Task task : this.taskList) {
            // do not check ToDo tasks
            if (task instanceof ToDo) {
                continue;
            }
            if (task.isSameDay(targetDate)) {
                tasksOnDate.add(task);
            }
        }
        return tasksOnDate;
    }
}

