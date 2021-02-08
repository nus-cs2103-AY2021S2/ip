package lihua.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private final List<Task> tasks;

    /**
     * Initializes the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task object to be added to the list.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes the task from the list at a specific index.
     *
     * @param i The index of the task to be deleted.
     * @return The task that gets deleted.
     * @throws IndexOutOfBoundsException If the i specified is invalid for the list.
     */
    public Task removeTask(int i) throws IndexOutOfBoundsException {
        return tasks.remove(i - 1);
    }

    /**
     * Retrieves the task from the list at a specific index.
     *
     * @param i The index of the task to be retrieved.
     * @return The task at index i.
     * @throws IndexOutOfBoundsException If the i specified is invalid for the list.
     */
    public Task getTaskDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i - 1).setDone(true);
        return tasks.get(i - 1);
    }

    /**
     * Finds tasks by a given keyword.
     *
     * @param keyWord The keyword specified.
     * @return A readable string representation of all tasks whose name contain the keyword as part of the substring.
     */
    public String listTasks(String keyWord) {
        String message = "";
        // tasks is not null, but maybe empty
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i - 1).getName().toLowerCase().contains(keyWord.toLowerCase())) {
                message += String.format("%d. %s\n", i, tasks.get(i - 1));
            }
        }
        if (message.equals("")) {
            message = String.format("Tasks containing '%s' do not exist :')", keyWord);
        } else {
            message = String.format("Here are all your tasks containing '%s':\n%sGood luck. :D", keyWord, message);
        }
        assert !message.equals("");
        return message;
    }

    /**
     * Lists down the content of the list in a readable string manner.
     *
     * @param date The date on which the tasks is expected to be finished.
     * @return If the specified date is null, returns the string representation for all tasks;
     * if the specified date is not null, returns the string representation of all the tasks on the date.
     */
    public String listTasks(LocalDate date) {
        String message = "";
        // tasks is not null, but maybe empty
        for (int i = 1; i <= tasks.size(); i++) {
            if (date != null) {
                // list on a specific date
                // date is not null here
                if (date.equals(tasks.get(i - 1).getDate())) {
                    // getDate() is not null here
                    message += String.format("%d. %s\n", i, tasks.get(i - 1));
                }
            } else {
                message += String.format("%d. %s\n", i, tasks.get(i - 1));
            }
        }
        if (message.equals("")) {
            message = "You do not have any task right now. Please add one first. :')";
        } else {
            message = "Here are all your tasks:\n" + message + "Good luck. :D";
        }
        assert !message.equals("");
        return message;
    }

    /**
     * Wraps the task list object inside a json array to be stored in hard disk.
     *
     * @return The json array representing the task list.
     */
    public JSONArray getJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.add(t.toJsonObject());
        }
        assert jsonArray != null;
        return jsonArray;
    }

    /**
     * Gets the current size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
