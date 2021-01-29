package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Ui is a class representing the user interface. The main function of the class is
 * to output result on the console.
 */
public class Ui {
    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     * @return input by the user in string.
     */
    String readCommand() {
        return sc.nextLine();
    }

    /**
     * Greets the user when the program is being initialized.
     * Outputs on the console.
     */
    void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Outputs a line to separate an user's input and program's output.
     */
    void showLine() {
        System.out.println("_______________________________________________________");
    }

    /**
     * Outputs the error message.
     *
     * @param msg The message that caused the error.
     */
    void showError(String msg) {
        System.out.println(msg);
    }

    /**
     *Outputs a goodbye message before the end of program.
     */
    public void showExit() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Outputs a loading error when the file containing data cannot be loaded
     * from the had disk.
     */
    void showLoadingError() {
        System.out.println("Unable to load the file. Empty list created.");
    }

    /**
     * Outputs when adding a task to the list is successful.
     *
     * @param task The added task.
     * @param size Current size of the list.
     */
    public void showAdd(String task, int size) {
        System.out.println("Got it. I've added this task:");
        showTaskAndSize(task, size);
    }

    /**
     * Outputs when deleting a task from the list is successful.
     *
     * @param task The deleted task.
     * @param size Current size of the list.
     */
    public void showDelete(String task, int size) {
        System.out.println("Noted, I've removed this task: ");
        showTaskAndSize(task, size);
    }

    /**
     * Outputs when marking a task as done is successful.
     * @param task The task that is marked as done.
     * @param size Current size of the list.
     */
    public void showDone(String task, int size) {
        System.out.println("Nice! I've mark this task as done");
        showTaskAndSize(task, size);
    }

    private void showTaskAndSize(String task, int size) {
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Lists out the current task(s) in the list. If the current list
     * is empty, outputs "There is currently no task in the list".
     *
     * @param list The list storing the tasks.
     */
    public void showList(List<Task> list) {
        if(list.isEmpty()){
            System.out.println("There is currently no task in the list.");
        }else{
            System.out.println("Here are the tasks in your list:");
            int index = 1;
            for(Task t: list){
                System.out.println(index + "." + t);
                index++;
            }
        }
    }
}
