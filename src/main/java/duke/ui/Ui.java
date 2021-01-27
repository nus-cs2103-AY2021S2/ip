package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

import java.util.LinkedList;

public class Ui {

    public Ui() {

    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printFormatted("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printFormatted(String msg) {
        String appendMsg = "____________________________________________________________\n"
                + msg
                + "\n____________________________________________________________";
        System.out.println(appendMsg);
    }

    public void showBye() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList tasks) {
        LinkedList<Task> lst = tasks.getList();
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        printFormatted(msg);
    }

    public void printAdded(TaskList tasks, Task task) {
        String msg = "Got it. I've added this task:\n"
                + "  " + task
                + "\nNow you have " + tasks.getList().size() + " tasks in the list.";
        printFormatted(msg);
    }

    public void printRemoved(TaskList tasks, Task task) {
        String msg = "Got it. I've removed this task:\n"
                + "  " + task
                + "\nNow you have " + tasks.getList().size() + " tasks in the list.";
        printFormatted(msg);
    }

    public void printMarked(Task task) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + task;
        printFormatted(msg);
    }
}
