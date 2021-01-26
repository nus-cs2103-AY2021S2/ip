import java.util.ArrayList;

/**
 * Deals with interactions with user
 *
 * @author Amanda Ang
 */
public class Ui {

    public void showGreetings() {
        System.out.println("Hello! I'm Amanda :)\nWhat can I do for you?");
    }

    public void showList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("%d. " + task + "\n", taskNumber);
            taskNumber++;
        }
    }

    public void showMatchingTasks(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("%d. " + task + "\n", taskNumber);
            taskNumber++;
        }
    }

    public void showDone(Task completedTask) {
        System.out.println("Nice! I've marked this task as done:\n" +  completedTask.toString());
    }

    public void showAddTask(ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1));
        System.out.printf("Now you have %d task(s) in the list.\n", list.size());
    }

    public void showInvalidTodo() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public void showDeleteTask(ArrayList<Task> list, Task deletedTask) {
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", list.size());
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String showInvalidCommand() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

}
