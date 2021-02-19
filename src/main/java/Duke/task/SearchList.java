package duke.task;

import java.util.ArrayList;

import duke.common.Response;

/**
 * List to record search results from find user command of keyword.
 */
public class SearchList {
    private ArrayList<Task> tasks;

    SearchList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds matched task to taskList.
     *
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Prints taskList.
     */
    public String list() {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            msg += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return Response.LIST.toString() + msg;
    }
}

