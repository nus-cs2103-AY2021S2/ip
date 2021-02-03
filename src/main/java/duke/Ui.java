package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Ui helps Danh's Duke interact with user by calling suitable method.
 * Ui has 2 main functions: read input and return output with print.
 */
class Ui {
    private final Scanner input;

    /**
     * Returns an Ui with integrated Scanner.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Read a command line entered by user and return it.
     *
     * @return the command line entered by user.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Check if user still enter command line or not.
     *
     * @return answer in form of boolean
     */
    public boolean stillHaveCommand() {
        return input.hasNextLine();
    }

    /**
     * Say Bye
     */
    public void echoBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Say Hi!
     */
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

    /**
     * Print all the tasks in taskList.
     *
     * @param taskList TaskList related.
     */
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

    /**
     * Print the results of adding a task to taskList.
     *
     * @param task      The task added
     * @param noOfTasks Number of tasks in taskList after adding.
     */
    public void echoAddToList(Task task, int noOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task.printTask());
        System.out.format("     Now you have %d tasks in the list.\n", noOfTasks);
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Print the results of marking a Task as done.
     *
     * @param task Task done.
     */
    public void echoMarkTaskDone(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Print the results of deleting a Task.
     *
     * @param task Task deleted.
     */
    public void echoDeleteTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Print the error message of a DukeException.
     *
     * @param err DukeException object related.
     */
    public void echoErrMsg(DukeException err) {
        System.out.println("    ____________________________________________________________\n"
                + err.getMessage() + "\n" + "    ____________________________________________________________\n");
    }

    /**
     * Print all the tasks of a specific day.
     *
     * @param taskList The tasklist related.
     * @param dateTime The day that we want to search for.
     */
    public void echoTaskThisDay(ArrayList<Task> taskList, LocalDateTime dateTime) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks on " + dateTime.toString().substring(0, 10) + ":");
        int index = 1;
        for (Task task : taskList) {
            if ((task instanceof Deadline && sameDay(((Deadline) task).getDlTime(), dateTime))
                    || (task instanceof Event && sameDay(((Event) task).geteTime(), dateTime))) {
                System.out.format("     %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * print all the tasks of Today.
     *
     * @param taskList The tasklist related.
     */
    public void echoTaskToday(ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks today:");
        int index = 1;
        for (Task task : taskList) {
            if ((task instanceof Deadline && sameDay(((Deadline) task).getDlTime(), LocalDateTime.now()))
                    || (task instanceof Event && sameDay(((Event) task).geteTime(), LocalDateTime.now()))) {
                System.out.format("     %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Print all the tasks that match the pattern of a find command.
     *
     * @param taskList The taskList related.
     * @param pattern The String pattern given by find command.
     */
    public void echoPrintFindResult(ArrayList<Task> taskList, String pattern) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks:");
        int index = 1;
        for (Task task : taskList) {
            if (task.getTaskName().contains(pattern)) {
                System.out.format("     %d. " + task.printTask() + "\n", index);
                index++;
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Helper function that checks if 2 dateTime refers to the same day or not.
     *
     * @param dateTime1 First dateTime input
     * @param dateTime2 Second dateTime input
     * @return answer in form of boolean.
     */
    public boolean sameDay(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return ((dateTime1.getDayOfYear() == dateTime2.getDayOfYear()) && (dateTime1.getYear() == dateTime2.getYear()));
    }
}
