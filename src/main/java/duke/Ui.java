package duke;

import java.util.Scanner;
import duke.task.Task;

/**
 * Represents interactions with the user.
 */
public class Ui {

    /**
     * Displays the welcome message.
     */
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Duke.\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the input commands from the user.
     */
    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays a message after a task is added to the list.
     * @param task Task to be added.
     * @param tasks Task List.
     */
    public void showAdded(Task task, TaskList tasks){
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.getSize() + " task"
                + (tasks.getSize()>1 ? "s in the list." : " in the list."));

    }

    /**
     * Displays a message after a task is deleted from the list.
     * @param task Task to be deleted.
     * @param tasks Task List.
     */
    public void showDeleted(Task task, TaskList tasks){
        System.out.println("Noted. I've removed this task:\n  " + task);
        System.out.println("Now you have " + tasks.getSize() + " task"
                + (tasks.getSize()>1 ? "s in the list." : " in the list."));

    }

    /**
     * Displays a message after a task is done.
     * @param id Task ID.
     * @param tasks Task List.
     */
    public void showDone(int id, TaskList tasks){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(id));
    }

    /**
     * Displays all the tasks when the user requests to view task list.
     * @param tasks Task List.
     */
    public void showList(TaskList tasks){
        System.out.println("Here are the tasks in your list:");

        if (tasks.getSize() == 0) {
            System.out.println("There is no task in the list.");
        } else {
            for (int i = 1; i <= tasks.getSize(); i++) {
                System.out.println(i + ". " + tasks.getTask(i));
            }
        }

    }

    /**
     * Displays error messages when an exception is caught.
     * @param error Error being caught.
     */
    public void showError(Exception error){
        System.out.println(error);
    }

    /**
     * Displays the divider line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays farewell message.
     */
    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
