package duke.component;

import java.util.ArrayList;

import duke.task.Task;

public class Ui {
    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Duke \n What can I do for you?";
    }

    /**
     * Prints bye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints done message.
     * @param t
     */
    public String showDone(Task t) {
        return "Nice! I've marked this task as done: \n"
                + t.toString();
    }

    /**
     * Prints delete message.
     * @param t
     * @param tl
     */
    public String showDelete(Task t, TaskList tl) {
        return "Noted. I've removed this task: \n"
                + t.toString() + "\n"
                + "Now you have "
                + tl.getTasks().size()
                + " tasks in the list.";
    }

    /**
     * Prints add message.
     * @param t
     * @param tl
     */
    public String showAdd(Task t, TaskList tl) {
        return "Got it. I've added this task: \n"
                + t.toString() + "\n"
                + "Now you have "
                + tl.getTasks().size()
                + " tasks in the list.";
    }

    /**
     * Prints not found message.
     */
    public String showNotFound() {
        return "Task not found!";
    }

    /**
     * Prints found tasks.
     * @param tasks
     */
    public String showFound(ArrayList<Task> tasks) {
        String result = "Here are the matching tasks in your list: \n";
        for (int i = 1; i <= tasks.size(); i++) {
            result += i + "." + tasks.get(i - 1).toString() + "\n";
        }
        return result;
    }

    /**
     * Prints all the task in the taskList.
     * @param tl
     */
    public String showList(TaskList tl) {
        String result = "Here are the tasks in your list: \n";
        ArrayList<Task> tasks = tl.getTasks();
        for (int i = 1; i <= tasks.size(); i++) {
            result += i + "." + tasks.get(i - 1).toString() + "\n";
        }
        return result;
    }
}
