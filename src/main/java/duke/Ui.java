package duke;

import duke.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
    }

    public String getCommand() {
        sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public String getDate() {
        String date = sc.nextLine();
        return date;
    }

    public void greetUser() {
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");
    }

    public void sayGoodbye() {
        System.out.println("Duchess: Bye, Have an awesome day!");
        sc.close();
    }

    public void requestDate() {
        System.out.println("Duchess: When will this event be?");
    }

    public void requestDeadline() {
        System.out.println("Duchess: When does this have to be done by?");
    }

    public void printList(ArrayList<Task> taskList) {
        String msg = "Here are the tasks in your list:";
        for(int i = 0; i < taskList.size(); i++) {
            msg+= "\n" +  (i + 1) + ". " + taskList.get(i);
        }
        System.out.println(msg);
    }

    public void checkedTask(Task task) {
        System.out.println("Duchess: Woohoo I've checked off this task:" + "\n" + task);
    }

    public void deletedTask(ArrayList<Task> taskList, Task task) {
        System.out.println("Duchess: As requested, i have removed this task:" + "\n"
                + task + "\n" + "U have " +  taskList.size() + " tasks in the list now :)");
    }

    public void addedTask(ArrayList<Task> taskList, Task task) {
        System.out.println("Duchess: Great! I have added:" + "\n" + task + "\n" + "U have " + taskList.size()
                + " tasks in the list now :)");
    }


}
