import java.util.ArrayList;

/**
 * Class containing the task list along with its associated functions.
 */
class TaskList {

    ArrayList<Task> tasks;
    Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Get the length of the tasks list.
     *
     * @return An Integer representing the length of the tasks list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void listTasks() {
        System.out.println(this.ui.FORMAT_LINE);
        System.out.println("You have the following task(s) in your list.");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());

        }
        System.out.println(this.ui.FORMAT_LINE);

    }

    /**
     * Adds a task to the task list.
     *
     * @param task An instance of Task representing the newly added task.
     */
    public void addTask(Task task) {
        this.tasks.add(task);

    }

    /**
     * Prints the newly added task details along with the new size of the task list
     *
     * @param task An instance of Task representing the newly added task
     */
    public void printAddedTask(Task task) {
        System.out.println(this.ui.FORMAT_LINE);
        System.out.println("Got it. I've added this task to your list:\n" +
                "   " + task.toString());
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
        System.out.println(this.ui.FORMAT_LINE);


    }

    /**
     * Marks the indicated given task as done.
     *
     * @param index An integer representing the index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        System.out.println(this.ui.FORMAT_LINE);
        System.out.println(" Good job! I've marked this task as done:\n" +
                "   " + task.toString());
        System.out.println(this.ui.FORMAT_LINE);

    }

    /**
     * Deletes the indicated task from the task list.
     *
     * @param index An integer representing the index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        System.out.println(this.ui.FORMAT_LINE);
        System.out.println(" Noted. I've removed this task:\n" +
                "   " + task.toString());
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
        System.out.println(this.ui.FORMAT_LINE);

    }

    /**
     * Searches for tasks based on a keyword.
     *
     * @param keyword A String indicating the keyword to search for.
     */
    public void findTask(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                filteredTasks.add(task);
            }
        }
        if (filteredTasks.size() == 0) {
            System.out.println("Sorry I could not find any matching tasks in your list.");
        } else {
            System.out.println(this.ui.FORMAT_LINE);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < filteredTasks.size(); i++) {
                Task task = filteredTasks.get(i);
                System.out.println(i + 1 + "." + task.toString());

            }
            System.out.println(this.ui.FORMAT_LINE);
        }
    }
}