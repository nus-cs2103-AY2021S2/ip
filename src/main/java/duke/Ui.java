package duke;

import java.util.ArrayList;

import duke.tasks.Task;


public class Ui {

    public Ui() {

    }

    public String displayAddedTask(Task task, int num) {
        return "☺ Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + num + " tasks in the list.";
    }

    public String displayDoneTask(Task task) {
        return "☺ Nice! I've marked this task as done: \n" + task;
    }

    public String displayDeletedTask(Task task, int num) {
        return "☺ Noted. I've removed this task: \n" + task + "\n"
                + "Now you have " + num + " tasks in the list.";
    }

    public String displayMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            sb.append(i).append(". ").append(t).append("\n");
        }
        return sb.toString();
    }

    public String displayList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            sb.append(i).append(". ").append(t).append("\n");
        }
        return sb.toString();
    }

    public String goodbyeMessage() {
        return "Bye. Hope to see you again soon! ☺";
    }
}
