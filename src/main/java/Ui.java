import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in = new Scanner(System.in);

    public Ui(){
        startDuke();
    }

    private static void startDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");
    }


    /**
     * Returns text input command by user.
     * If the user input is null, empty String is returned.
     *
     * @return Input command Line.
     */
    public String getUserCommand(){
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showResultToUser(TaskList tasks, TaskResult taskResult){
        String feedbackToUser = taskResult.getFeedbackToUser();
        TaskList relatedTaskList = taskResult.getTaskList();
        Task relatedTask = taskResult.getTask();
        switch (feedbackToUser){
        case "add":
            showAddMessage(relatedTask, tasks.getSize());
            break;

        case "complete":
            showCompleteMessage(relatedTask);
            break;

        case "delete":
            showDeleteMessage(relatedTask, tasks.getSize());
            break;

        case "display":
            printMyTask(tasks);
            break;

       case "find":
           printMatchingTask(relatedTaskList);
           break;
        }
    }


    /**
     * Print all tasks contained in a TaskList object.
     *
     * @param taskList TaskList object to be printed.
     */
    private void printMyTask(TaskList taskList) {

            System.out.println("---------------------------------------------");
            System.out.println("Here are the tasks in your list:");

            // loop through list and print every task in a new line
            int len = taskList.getSize();
            ArrayList<Task> tasks = taskList.getTaskList();
            for (int i = 1; i < len + 1; i++) {
                Task curTask = tasks.get(i - 1);
                System.out.println(i + ". " + curTask);
            }

            System.out.println("---------------------------------------------");
    }

    /**
     * Print matching tasks contained in an ArrayList.
     *
     * @param matchingTask List of tasks to be printed.
     */
    private void printMatchingTask(TaskList matchingTask) {

            System.out.println("---------------------------------------------");
            System.out.println("Here are the matching tasks in your list:");

            // loop through list and print every task in a new line
            int len = matchingTask.getSize();
        ArrayList<Task> tasks = matchingTask.getTaskList();
            for (int i = 1; i < len + 1; i++) {
                Task curTask = tasks.get(i - 1);
                System.out.println(i + ". " + curTask);
            }

            System.out.println("---------------------------------------------");
    }

    public void showGoodbyeMessage(){
        System.out.println("---------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
    }

    private void showCompleteMessage(Task task){
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("---------------------------------------------");
    }

    private void showDeleteMessage(Task task, int listSize){
        System.out.println("---------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
        System.out.println("---------------------------------------------");
    }

    private void showAddMessage(Task task, int listSize){
        System.out.println("---------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
        System.out.println("---------------------------------------------");
    }

    public void showLoadingError(){
        System.out.println("---------------------------------------------");
        System.out.println("OOPS!! Duke fails to load, please restart!");
        System.out.println("---------------------------------------------");
    }

    public void showErrorMessage(String message){
        System.out.println("---------------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------------");
    }
}
