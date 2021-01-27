import java.util.ArrayList;
import java.util.List;

/**
 * A class which maintains a list which stores the added tasks.
 */
public class TaskList extends ArrayList<Task> {
    static List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Deletes a task of the specified index from the taskList.
     *
     * @param num Index of the task in the taskList that needs to be deleted.
     */
    public static void delete(int num) {
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n     " +
                tasks.get(num - 1) + "\n" +
                "     Now you have " + (tasks.size() - 1) + " tasks in the list.\n" +
                "    ____________________________________________________________");
        tasks.remove(num - 1);
    }

    /**
     * List all the tasks that is in the taskList.
     */
    public static void list() {
        String s = "";
        if (tasks.size() == 0) {
            System.out.println("    ____________________________________________________________\n     " +
                    "Here are the tasks in your list:\n    " +
                    "____________________________________________________________\n");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            s += (i + 1) + "." + tasks.get(i) + "\n";
            if (i != tasks.size() - 1) {
                s += "     ";
            }
        }
        System.out.println("    ____________________________________________________________\n     " +
                "Here are the tasks in your list:\n     " + s +
                "    ____________________________________________________________\n");
    }

    /**
     * Add the task to the taskList.
     *
     * @param task Add the task given to the taskList.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * Mark a task of the specified index in the taskList as done.
     *
     * @param num Mark the task of the specified index as done.
     */
    public static void done(int num) {
        Task task = tasks.get(num - 1);
        task.markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + task + "\n" +
                "    ____________________________________________________________\n");
    }
}
