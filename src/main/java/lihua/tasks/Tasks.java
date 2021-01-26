package lihua.tasks;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import org.json.simple.JSONArray;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private final List<Task> tasks;

    /**
     * Initialize the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task removeTask(int i) throws IndexOutOfBoundsException {
        return tasks.remove(i - 1);
    }

    public Task getTaskDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i - 1).setDone(true);
        return tasks.get(i - 1);
    }

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
        return message;
    }

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
        return message;
    }

    public JSONArray getJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Task t : tasks) {
            jsonArray.add(t.toJsonObject());
        }
        return jsonArray;
    }

    public int getSize() {
        return tasks.size();
    }
}