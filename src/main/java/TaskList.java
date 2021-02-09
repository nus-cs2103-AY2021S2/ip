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
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n     "
                + tasks.get(num - 1) + "\n"
                + "     Now you have " + (tasks.size() - 1) + " tasks in the list.\n"
                + "    ____________________________________________________________");
        Duke.respond = "Noted. I've removed this task:\n" + tasks.get(num - 1) + "\nNow you have "
                + (tasks.size() - 1) + " tasks in the list.";
        tasks.remove(num - 1);
    }

    /**
     * Finds task and print out the details of the tasks that is related to the keyword provided.
     *
     * @param keyword Parts of the description of the task that a user wants to find.
     */
    public static void find(String keyword) {
        String print = "";
        int num = 1;
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            Task task = TaskList.tasks.get(i);
            String taskDescription = task.description;
            String word = "";
            for (int j = 0; j < taskDescription.length(); j++) {
                if (taskDescription.charAt(j) == ' ') {
                    if (word.equals(keyword)) {
                        print += num + "." + task + "\n";
                        num++;
                    }
                    word = "";
                } else {
                    word += taskDescription.charAt(j);
                    if (j == taskDescription.length() - 1) {
                        if (word.equals(keyword)) {
                            print += num + "." + task + "\n";
                            num++;
                        }
                    }
                }
            }
        }
        print = "Task(s) related to the keyword :\n" + print;
        System.out.println(print);
        Duke.respond = print;
    }

    /**
     * List all the tasks that is in the taskList.
     */
    public static void list() {
        String s = "";
        if (tasks.size() == 0) {
            Duke.respond = "You have no task in your list!";
            System.out.println("    ____________________________________________________________\n     "
                    + "You have no task in your list!\n    "
                    + "____________________________________________________________\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                s += (i + 1) + "." + tasks.get(i) + "\n";
            }
            Duke.respond = "Here are the tasks in your list:\n" + s;
            System.out.println("    ____________________________________________________________\n     " +
                    "Here are the tasks in your list:\n     " + s +
                    "    ____________________________________________________________\n");
        }
    }

    /**
     * Add the task to the taskList. Prevent duplicated task that has already existed.
     *
     * @param task Add the task given to the taskList.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        Duke.respond = "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size()
                + " tasks in the list.";
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n"
                + "       " + task + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Mark a task of the specified index in the taskList as done.
     *
     * @param num Mark the task of the specified index as done.
     */
    public static void done(int num) {
        Task task = tasks.get(num - 1);
        task.markAsDone();
        Duke.respond = "Nice! I've marked this task as done:\n" + task;
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done: \n"
                + "       " + task + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Check if the new task to be added to taskList is duplicated.
     *
     * @param task Task that is to be added to taskList.
     * @return true if task has already existed. Otherwise return false.
     */
    public static boolean isDuplicate(Task task) {
        boolean isDuplicate;
        for (int i = 0; i < tasks.size(); i++) {
            Task taskFromList = tasks.get(i);
            if (taskFromList instanceof ToDo && task instanceof ToDo) {
                isDuplicate = taskFromList.description.equals(task.description);
                if (isDuplicate) {
                    return true;
                }
            } else if (taskFromList instanceof Event && task instanceof Event) {
                boolean isSameDescription = taskFromList.description.equals(task.description);
                boolean isSameDate = ((Event) taskFromList).at.equals(((Event) task).at);
                boolean isSameStartTime = ((Event) taskFromList).start.equals(((Event) task).start);
                boolean isSameEndTime = ((Event) taskFromList).end.equals(((Event) task).end);
                isDuplicate = isSameDescription && isSameDate && isSameStartTime && isSameEndTime;
                if (isDuplicate) {
                    return true;
                }
            } else if (taskFromList instanceof Deadline && task instanceof Deadline) {
                boolean isSameDescription = taskFromList.description.equals(task.description);
                boolean isSameDate = ((Deadline) taskFromList).by.equals(((Deadline) task).by);
                boolean isSameTime = ((Deadline) taskFromList).time.equals(((Deadline) task).time);
                isDuplicate = isSameDescription && isSameDate && isSameTime;
                if (isDuplicate) {
                    return true;
                }
            }
        }
        return false;
    }
}
