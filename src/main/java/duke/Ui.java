package duke;

import duke.task.Task;
import java.util.List;

public class Ui {
    public Ui() {
    }

    public void printDivider() {
        System.out.println("    _________________________________________________");
    }

    public void welcome() {
        String logo = "   ____        _        \n"
                + "               |  _ \\ _   _| | _____\n"
                + "               | | | | | | | |/ / _ \\\n"
                + "               | |_| | |_| |   <  __/\n"
                + "               |____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println("     Hey! I'm" + logo
                + "\n     How may I help you?");
        printDivider();
    }

    public void exit() {
        printDivider();
        System.out.println("     Bye. Hope to see you again soon!");
        printDivider();
    }

    public void printLoadingError() {
        System.out.println("\n     No existing file found! Created a new file. :)");
    }

    public void printTaskList(List<Task> list) {
        if (list.size() == 0) {
            printDivider();
            System.out.println("     There is currently no task in your list.");
        } else {
            printDivider();
            System.out.println("     Here are the tasks in your list:");
        }

        for (int i = 0; i < list.size(); i++) {
            int number = 1 + i;
            System.out.println("     " + number + ". " + list.get(i));
        }

        printDivider();
    }

    public void printAddTask(Task newTask, List<Task> list) {
        printDivider();
        System.out.println("      Yes sir! I've added this task:\n"
                + "            " + newTask + "\n"
                + "      Now you have " + list.size() + " tasks in the list.");
        printDivider();
    }

    public void printDoneTask(int taskNumber, List<Task> list) {
        printDivider();
        System.out.println("     Nice! I've marked this task as done:\n"
                + "        " + list.get(taskNumber));
        printDivider();
    }

    public void printDeleteTask(int taskNumber, List<Task> list) {
        printDivider();
        System.out.println("     Noted. I've removed this task:\n"
                + "        " + list.get(taskNumber)
                + "\n     Now you have " + (list.size() - 1) + " tasks in the list.");
        printDivider();
    }
}
