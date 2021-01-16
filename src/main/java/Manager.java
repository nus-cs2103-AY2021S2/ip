import java.util.ArrayList;
import java.util.List;

public class Manager {
    public static String line = "------------------------------------------------------";
    private final List<Task> ls;

    Manager() {
        this.ls = new ArrayList<>();
    }

    public void addTask(Task task) {
        String res = "\t" + line + "\n\tGot it. I've added this task:\n\t\t" + task.toString() + "\n";
        this.ls.add(task);
        int numOfTasks = ls.size();
        res += "\tNow you have " + numOfTasks + " tasks in the list\n\t" + line;
        System.out.println(res);
    }

    public void finishTask(int index) {
        Task task = this.ls.get(index - 1);
        task.markAsDone();
        String res = "\t" + line + "\n\t" + "Nice! I've marked this task as done: \n\t\t" + task + "\n\t" + line;
        System.out.println(res);

    }

    public void  listTask() {
        String res = "\t" + line + "\n\tHere are the tasks in your list:\n";
        for (int i = 0; i < ls.size(); i++) {
            res += "\t" + (i + 1) + "." + ls.get(i) + "\n";
        }
        res += "\t" + line;
        System.out.println(res);
    }
}

