package duke;

import java.util.ArrayList;

//user interaction
public class Ui {

    protected static final String HORIZONTAL_RULE = "____________________________________________________________";

    public Ui() {

    }

    public void greetings() {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm your personal assistant Duke\n" +
                "How can I assist you?\n" +
                "____________________________________________________________");
    }

    public void exit() {
        System.out.println("Bye. Till next time!");
        System.out.println(HORIZONTAL_RULE);
    }

    public void addTaskMessage(ArrayList<Task> list, Task addedTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

    public void deleteTaskMessage(ArrayList<Task> list, Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

    public void listAllTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task : list) {
            System.out.println(String.format("%d. %s", num, task));
            num++;
        }
        System.out.println(HORIZONTAL_RULE);
    }

    public void checkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println(HORIZONTAL_RULE);
    }

}
