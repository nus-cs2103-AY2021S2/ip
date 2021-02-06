import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manager to manage a list of tasks
 */
public class TaskList {
    private final List<Task> ls = new ArrayList<>();
    private final Storage storage;

    TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * load the task in file to arrayList
     */
    public void addTaskFromFile() throws IOException {
        File file = this.storage.getFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            Task task = Parser.parseFileInput(str);
            this.ls.add(task);
        }
    }

    public List<Task> getList() {
        return this.ls;
    }


    /**
     * Adds a given task to the list.
     * @param task given task
     */
    public String addTask(Task task) {
        String res = "\t" + "\n\tGot it. I've added this task:\n\t\t" + task.toString() + "\n";
        this.ls.add(task);
        int numOfTasks = ls.size();
        res += "\tNow you have " + numOfTasks + " tasks in the list\n\t";
        return res;
    }
    /**
     * Search by keyword
     * @param keyword of a certain task
     */

    public String find(String keyword) {
        String res = "";
        if (ls.isEmpty()) {
            res += "\t" + "No task to find!\n";
        } else {
            res = "\t" + "\n\tHere are the matching tasks in your list:\n";
            List<Task> newList = new ArrayList<>();
            for (Task task : this.ls) {
                if (task.toString().contains(keyword)) {
                    newList.add(task);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                res += "\t" + (i + 1) + "." + newList.get(i) + "\n";
            }
        }

        return res;
    }

    /**
     * Mark the task as done
     * @param index
     */

    public String finishTask(int index) {
        Task task = this.ls.get(index - 1);
        task.markAsDone();
        String res = "\t" + "\n\t" + "Nice! I've marked this task as done: \n\t\t" + task + "\n\t";
        return res;

    }

    /**
     * Delete a certain task
     * @param index index of a certain task
     */

    public String deleteTask(int index) {
        assert index < 0 : "index should not be not be negative";
        Task task = this.ls.get(index - 1);
        this.ls.remove(index - 1);
        int len = this.ls.size();
        String res = "\t" + "\n\t" + " Noted. I've removed this task:\n\t\t" + task
                + "\n\tNow you have " + len + " tasks in the list.\n\t";
        return res;

    }

    /**
     * List all the tasks in taskList
     */

    public String listTask() {
        String res = "";
        if (ls.isEmpty()) {
            res = "\t" + "\n\tWell Done! All task has been completed\n";
        } else {
            res = "\t" + "\n\tHere are the tasks in your list:\n";
            for (int i = 0; i < ls.size(); i++) {
                res += "\t" + (i + 1) + "." + ls.get(i) + "\n";
            }
        }

        return res;
    }

    @Override
    public String toString() {
        String res = "";
        for (Task task : this.ls) {
            res += task.toString() + "\n";
        }
        return res;
    }
}

