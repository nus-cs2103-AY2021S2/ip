import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Ui {
    private BufferedReader reader;

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    void intro() {
        System.out.println("Hey there! This is Kums");
        System.out.println("How can i help?");
    }

    void showLine() {
        System.out.println("---------------------------------------");
    }

    void showError(String message) {
        System.out.println(message);
    }

    void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    String readCommand() throws IOException {
        return this.reader.readLine();
    }

    void showLoadingError() {
        System.out.println("OOPS! The file hasn't been created yet");
    }


    /**
     * Prints lines to show the user what task has been added.
     *
     * @param tasks TaskList of all the tasks
     */
    void addedTask(TaskList tasks) {
        int numberOfTasks = tasks.getListLength();
        Task task = tasks.getList().get(numberOfTasks - 1);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + (numberOfTasks) + " tasks in the list.");
    }


    /**
     * prints all the task in the list
     *
     * @param tasks TaskList containing all the user's tasks
     */
    void printList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }


    /**
     * notifies the user that a specific task has been removed from the TaskList.
     *
     * @param task Task that has been removed
     * @param listLength number of tasks left in the list
     */
    void deletedTask(Task task, int listLength) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + listLength + " tasks in the list.");
    }


    /**
     * notifies the user that a specific task has been completed.
     *
     * @param task Task that has just been completed
     */
    void didTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    void printFilteredList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        int listLength = tasks.getListLength();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < listLength; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }
}
