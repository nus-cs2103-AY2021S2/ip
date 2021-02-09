import java.util.ArrayList;

public class Ui {

    public Ui() {
    }

    public void showWelcomeMessage() {
        System.out.println("Hey, I'm Duke!\n" + "How can I help you?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Aw. It was nice chatting with you! Don't forget me! :)");
    }

    public void showInvalidTaskMessage() {
        System.out.println("Oops! I don't know what this means! :(");
    }

    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + TaskList.taskListSize + " task(s) in the list.");
    }

    public void showTaskDeleted(Task task) {
        System.out.println("Gotcha. I've removed this task:\n"
                + task.toString()
                + "\nNow you have " + TaskList.taskListSize + " task(s) in the list.");
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice job! I've marked this task as done:\n"
                + task.toString());
    }

    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("Looks like you have no tasks currently. Add some tasks!");
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task item = taskList.get(i);
            System.out.println((i + 1) + "." + item.toString());
        }
    }
}
