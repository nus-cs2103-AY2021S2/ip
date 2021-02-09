import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task specified by the input index from the TaskList.
     *
     * @param index Index of the file to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);

        return task;
    }

    /**
     * Sets the task specified by the input index from the TaskList as done.
     *
     * @param index Index of the file to be set as done.
     * @return The done task.
     */
    public Task doneTask(int index) {
        Task task = tasks.get(index - 1);
        task.setDone();

        return task;
    }

    /**
     * Prints all the tasks in the TaskList.
     */
    public String printTasks() {
        if (tasks.isEmpty()) {
            return "It is empty boss.";
        }

        StringBuilder allTasks = new StringBuilder("You have ")
                .append(tasks.size())
                .append(" task(s) in the list:\n");

        int i = 1;
        for (Task task : tasks) {
            allTasks.append(i).append(". ").append(task).append("\n");

            i++;
        }

        return allTasks.toString();
    }

    /**
     * Prints all the Tasks with String representation that contains the query term.
     * @param query The query term that the command string must include.
     */
    public String findTasks(String query) {
        if (tasks.isEmpty()) {
            return "Oops, the task list is empty boss, there's nothing to find!";
        }

        StringBuilder foundTasks = new StringBuilder("Here you go boss:\n");

        int i = 1;
        for (Task task : tasks) {
            String string = task.toString();
            if (string.contains(query)) {
                foundTasks.append(i).append(". ").append(string).append("\n");
            }
            i++;
        }
        return foundTasks.toString();

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
