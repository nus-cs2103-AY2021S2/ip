import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Ui {
    private BufferedReader reader;

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    String intro() {
        return "Hey there! This is Einstein" + "\n" + "How can I help?";
    }

    String showError(String message) {
        return message;
    }

    String bye() {
        return "Bye! Please press the red button on the top left corner to close the application.";
    }

    String readCommand() throws IOException {
        return this.reader.readLine();
    }

    String showLoadingError() {
        return "OOPS! The file hasn't been created yet";
    }


    /**
     * Returns a string to notify the user a task has been added.
     *
     * @param tasks TaskList containing all the user's tasks.
     * @return A string stating that the given task has been added to the list.
     */
    String addedTask(TaskList tasks) {
        int numberOfTasks = tasks.getListLength();
        Task task = tasks.getList().get(numberOfTasks - 1);
        String result = "Got it. I've added this task:" + "\n" + "    " + task.toString() + "\n"
                + "Now you have " + (numberOfTasks) + " tasks in the list.";
        return result;
    }


    /**
     * Prints all the task in the list.
     *
     * @param tasks TaskList containing all the user's tasks
     */
    String printList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            result = result + "\n" + (i + 1) + ") " + task.toString();
        }
        return result;
    }


    /**
     * Returns a string to notify the user a task has been deleted.
     *
     * @param task Task that has been removed.
     * @param listLength Number of tasks left in the list.
     */
    String deletedTask(Task task, int listLength) {
        String result = "Noted. I've removed this task:" + "\n" + task.toString() + "\n" + "Now you have "
                + listLength + " tasks in the list.";
        return result;
    }


    /**
     * Returns a string to notify the user that a specific task has been completed.
     *
     * @param task Task that has just been completed.
     */
    String didTask(Task task) {
        String result = "Nice! I've marked this task as done:" + "\n" + "    " + task.toString();
        return result;
    }

    /**
     * Prints a filtered list based a phrase given by the user.
     *
     * @param tasks TaskList containing all the user's tasks.
     * @return A string containing the filtered list.
     */
    String printFilteredList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        int listLength = tasks.getListLength();
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < listLength; i++) {
            Task task = list.get(i);
            result = result + "\n" + (i + 1) + ") " + task.toString();
        }
        return result;
    }

    /**
     * Returns a string to notify the user that
     * a priority has been added to a certain task.
     *
     * @param task Task to which the priority has been added to.
     * @return String to inform the user the priority has been added.
     */
    public String addedPriority(Task task) {
        String result = "Nice! I've marked this task's priority as " + task.priority
                + "\n" + "    " + task.toString();
        return result;
    }
}
