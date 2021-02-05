import java.util.ArrayList;

/**
 * Deals with interactions with user
 *
 * @author Amanda Ang
 */
public class Ui {

    public String showList(ArrayList<Task> list) {
        String output = "Here are the tasks in your list:\n";
        int taskNumber = 1;
        for (Task task : list) {
            output += (taskNumber + ". " + task + "\n");
            taskNumber++;
        }
        return output;
    }

    public String showMatchingTasks(ArrayList<Task> list) {
        String output = "Here are the matching tasks in your list:\n";
        int taskNumber = 1;
        for (Task task : list) {
            output += (taskNumber + ". " + task + "\n");
            taskNumber++;
        }
        return output;
    }

    public String showDone(Task completedTask) {
        return "Nice! I've marked this task as done:\n" + completedTask.toString();
    }

    public String showAddTask(ArrayList<Task> list) {
        return "Got it. I've added this task:\n" + list.get(list.size() - 1)
                + "\nNow you have " +  list.size() + " task(s) in the list.\n";
    }

    public String showInvalidTodo() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    public String showDeleteTask(ArrayList<Task> list, Task deletedTask) {
        return "Noted. I've removed this task:\n" + deletedTask.toString()
                + "\nNow you have " + list.size() + " tasks in the list.\n";
    }

    public String showClear() {
        return "Task list has been cleared!";
    }

    public String showSave() {
        return "Task list has been updated and saved!";
    }

    public String showInvalidCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

}
