package duke.task;

import java.util.ArrayList;

import duke.common.Response;



public class SearchList {
    private ArrayList<Task> tasks;

    SearchList() {
        tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Prints taskList.
     */
    public void list() {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            msg += (i + 1) + "." + tasks.get(i) + "\n";
        }
        enclose(Response.LIST.toString() + msg);
    }

    /**
     * Prints output to user in generic format.
     */
    public void enclose(String reply) {
        System.out.println("---------------------------------------");
        System.out.println(reply);
        System.out.println("---------------------------------------\n");
    }

    public String status() {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }
}

