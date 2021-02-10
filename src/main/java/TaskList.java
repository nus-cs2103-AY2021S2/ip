import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class containing the task list along with its associated functions.
 */
class TaskList {

    private final ArrayList<Task> tasks;
    private final Ui ui = new Ui();

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
    public int listSize() {
        return this.tasks.size();
    }

    /**
     * Get all the tasks in the task list.
     *
     * @return A String containing details of all the tasks in the tasks list.
     */
    public String listTasks() {
        String output = "";
        output += ui.getLine() + "\n";
        output += "You have the following task(s) in your list.\n";
        for (int i = 0; i < this.listSize(); i++) {
            Task task = this.tasks.get(i);
            output += i + 1 + "." + task.toString() + "\n";

        }
        output += "\n" + ui.getLine();

        return output;
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
     * Get the newly added task details along with the new size of the task list.
     *
     * @param task An instance of Task representing the newly added task.
     * @return String containing details of the newly added task.
     */
    public String addedTaskDetails(Task task) {
        String output = "";
        output += ui.getLine();
        output += "\nGot it. I've added this task to your list:\n"
                + "   " + task.toString();
        output += "\nNow you have " + this.tasks.size() + " task(s) in the list.\n";
        output += ui.getLine();

        return output;
    }

    /**
     * Marks the indicated given task as done.
     *
     * @param index An integer representing the index of the task to be marked as done.
     * @return String containing details of the task marked as done.
     */
    public String markAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        String output = "";
        output += ui.getLine();
        output += "\n Good job! I've marked this task as done:\n"
                + "   " + task.toString() + "\n";
        output += ui.getLine();
        return output;

    }

    /**
     * Deletes the indicated task from the task list.
     *
     * @param index An integer representing the index of the task to be deleted.
     * @return String containing details of the deleted task.
     */
    public String deleteTask(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        String output = "";
        output += ui.getLine();
        output += "\n Noted. I've removed this task:\n"
                + "   " + task.toString() + "\n";
        output += "Now you have " + this.tasks.size() + " task(s) in the list.\n";
        output += ui.getLine();
        return output;

    }

    /**
     * Searches for tasks based on a keyword.
     *
     * @param keyword A String indicating the keyword to search for.
     * @return String containing details of all the tasks found in the search.
     */
    public String findTask(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String output = "";
        for (Task task : this.tasks) {
            boolean containsKeyword = task.getDescription().toLowerCase().contains(keyword.toLowerCase());
            if (containsKeyword) {
                filteredTasks.add(task);
            }
        }
        if (filteredTasks.size() == 0) {
            output += "Sorry I could not find any matching tasks in your list.\n";
        } else {
            output += ui.getLine();
            output += "\nHere are the matching tasks in your list:\n";
            for (int i = 0; i < filteredTasks.size(); i++) {
                Task task = filteredTasks.get(i);
                output += i + 1 + "." + task.toString() + "\n";

            }
            output += "\n" + ui.getLine();
        }
        return output;
    }

    /**
     * Sorts the task list alphabetically based on description.
     *
     * @return String indicating successful sorting of the list.
     */
    public String sortAlphabetically() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                String description1 = t1.getDescription().toLowerCase();
                String description2 = t2.getDescription().toLowerCase();
                System.out.println(description1);
                System.out.println(description2);
                return description1.compareTo(description2);
            }
        });
        String output = "Your tasks have been sorted alphabetically.\n";
        output = output + "Use the 'list' command to view them.";
        return output;
    }
}
