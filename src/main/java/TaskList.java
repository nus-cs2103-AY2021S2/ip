import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Task list.
 */
public class TaskList {
    /***
     * Arraylist of Tasks (Todo, Deadline, Event).
     */
    private final ArrayList<Task> tasksList;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     *
     * @param loadTasksList the load tasks list
     */
    public TaskList(ArrayList<Task> loadTasksList) {
            this.tasksList = new ArrayList<>();
            this.tasksList.addAll(loadTasksList);
    }

    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    /**
     * Gets tasks count.
     *
     * @return the tasks count
     */
    public int getTasksCount() {
        return this.tasksList.size();
    }

    /**
     * Adds a new Task to the Task list.
     *
     * @param task the task
     */
    public void addTask(Task task) {
        //System.out.println("i reached addTask");
        this.tasksList.add(task);
    }

    /**
     * Deletes a Task in Task List based on index.
     *
     * @param index the task index
     */
    public void delTask(int index) {
        this.tasksList.remove(index);
    }
    /**
     * Sorts Tasks in Task List chronologically.
     *
     * @return  Sorted Task List
     */
    public TaskList dateSort() {
        ArrayList<TimeTask> sortedTasks = new ArrayList<>();
        ArrayList<Task> nonSortedTasks = new ArrayList<>();
        ArrayList<Task> chronoTasks = new ArrayList<>();

        for (int i = 0; i < this.getTasksCount(); i++) {
            if (this.getTasksList().get(i) instanceof Todo) {
                nonSortedTasks.add(this.getTasksList().get(i));
            } else if (this.getTasksList().get(i) instanceof Deadlines) {
                sortedTasks.add(
                        new TimeTask((Deadlines) this.getTasksList().get(i)));

            } else {
                assert (this.getTasksList().get(i) instanceof Events)
                        : "dateSort() error";
                sortedTasks.add(
                        new TimeTask((Events) this.getTasksList().get(i)));
            }
        }
        Collections.sort(sortedTasks, Collections.reverseOrder());
        //Collections.sort(sortedTasks);

        for (int i = 0; i < sortedTasks.size(); i++) {
            chronoTasks.add(sortedTasks.get(i).getTask());
        }
        chronoTasks.addAll(0,nonSortedTasks);
        //chronoTasks.addAll(nonSortedTasks);

        return new TaskList(chronoTasks);

    }

    /***
     * Provides the complete current list of Tasks.
     * @return String of current list of Tasks
     */

    @Override
    public String toString() {
        //List Tasks
        String tasksListStr = "";
        for (int i = 0; i < this.tasksList.size(); i++) {
            tasksListStr = tasksListStr + (i + 1) + ". "
                    + this.tasksList.get(i).toString();
            if (i != this.tasksList.size() - 1) {
                tasksListStr = tasksListStr + "\n";
            }
        }
        return tasksListStr;
    }
}
