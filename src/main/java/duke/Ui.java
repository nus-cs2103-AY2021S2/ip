package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String readCommand() {
        return input.nextLine();
    }

    public boolean stillHaveCommand() {
        return input.hasNextLine();
    }

    public void echoBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    public void echoHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Danh's Duke\nWhat can I do for you, Mr Danh?");
        System.out.println("    ____________________________________________________________\n");
    }

    public void echoPrintList(ArrayList<Task> taskList) {
        int index = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.format("     %d. " + task.printTask() + "\n", index);
            index++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void echoAddToList(Task task, int noOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task.printTask());
        System.out.format("     Now you have %d tasks in the list.\n", noOfTasks);
        System.out.println("    ____________________________________________________________\n");
    }

    public void echoMarkTaskDone(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }

    public void echoDeleteTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }

    public void echoErrMsg(DukeException err) {
        System.out.println("    ____________________________________________________________\n" + err.getMessage() +
                "\n" + "    ____________________________________________________________\n");
    }

    public void echoTaskThisDay(ArrayList<Task> taskList, LocalDateTime dateTime) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks on " + dateTime.toString().substring(0,10) + ":");
        int index = 1;
        for (Task task : taskList) {
            if ((task instanceof Deadline && sameDay(((Deadline) task).dlTime, dateTime))
                    || (task instanceof Event && sameDay(((Event) task).eTime, dateTime))) {
                System.out.format("     %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public boolean sameDay(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return ((dateTime1.getDayOfYear() == dateTime2.getDayOfYear()) && (dateTime1.getYear() == dateTime2.getYear()));
    }

    public void echoTaskToday(ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks today:");
        int index = 1;
        for (Task task : taskList) {
            if ((task instanceof Deadline && sameDay(((Deadline) task).dlTime, LocalDateTime.now()))
                    || (task instanceof Event && sameDay(((Event) task).eTime, LocalDateTime.now()))) {
                System.out.format("     %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
