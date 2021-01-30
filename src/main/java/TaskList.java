import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manager to manage a list of tasks
 */
public class TaskList {
    private static String line = "------------------------------------------------------";
    private final List<Task> ls = new ArrayList<>();
    private final Storage storage;

    TaskList(Storage storage){
        this.storage = storage;
    }

    public void addTaskFromFile() throws IOException {
        File file = this.storage.getFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
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
    public void addTask(Task task) {
        String res = "\t" + line + "\n\tGot it. I've added this task:\n\t\t" + task.toString() + "\n";
        this.ls.add(task);
        int numOfTasks = ls.size();
        res += "\tNow you have " + numOfTasks + " tasks in the list\n\t" + line;
        System.out.println(res);
    }
    /**
     * Search by keyword
     * @param keyword of a certain task
     */

    public void find(String keyword) {
        String res = "";
        if (ls.isEmpty()) {
            res = "\t" + line + "No task to find!\n";
        } else {
            res = "\t" + line + "\n\tHere are the matching tasks in your list:\n";
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

        res += "\t" + line;
        System.out.println(res);
    }

    public void finishTask(int index) {
        Task task = this.ls.get(index - 1);
        task.markAsDone();
        String res = "\t" + line + "\n\t" + "Nice! I've marked this task as done: \n\t\t" + task + "\n\t" + line;
        System.out.println(res);

    }

    /**
     * Delete a certain task
     * @param index index of a certain task
     */

    public void deleteTask(int index) {
        Task task = this.ls.get(index - 1);
        this.ls.remove(index-1);
        int len = this.ls.size();
        String res = "\t" + line + "\n\t" + " Noted. I've removed this task:\n\t\t" + task +
                "\n\tNow you have " + len +" tasks in the list.\n\t" + line;
        System.out.println(res);

    }

    /**
     * List all the tasks in taskList
     */

    public void listTask() {
        String res = "";
        if(ls.isEmpty()){
            res = "\t" + line + "\n\tWell Done! All task has been completed\n";
        } else {
            res = "\t" + line + "\n\tHere are the tasks in your list:\n";
            for (int i = 0; i < ls.size(); i++) {
                res += "\t" + (i + 1) + "." + ls.get(i) + "\n";
            }
        }

        res += "\t" + line;
        System.out.println(res);
    }
}

