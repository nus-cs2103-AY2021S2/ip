import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in = new Scanner(System.in);
    private final String separator = "---------------------------------------------";

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

    public String showResultToUser(TaskList tasks, TaskResult taskResult){
        String feedbackToUser = taskResult.getFeedbackToUser();
        TaskList relatedTaskList = taskResult.getTaskList();
        Task relatedTask = taskResult.getTask();

        String responseToUser = "OOPS!! Something is wrong.";

        switch (feedbackToUser){
        case "add":
            responseToUser = showAddMessage(relatedTask, tasks.getSize());
            break;

        case "complete":
            responseToUser = showCompleteMessage(relatedTask);
            break;

        case "delete":
            responseToUser = showDeleteMessage(relatedTask, tasks.getSize());
            break;

        case "display":
            responseToUser = printMyTask(tasks);
            break;

       case "find":
           responseToUser = printMatchingTask(relatedTaskList);
           break;
        }
        return responseToUser;
    }


    /**
     * Print all tasks contained in a TaskList object.
     *
     * @param taskList TaskList object to be printed.
     */
    private String printMyTask(TaskList taskList) {
        String responseToUser = separator + "\n" + "Here are the tasks in your list:";

        System.out.println("---------------------------------------------");
        System.out.println("Here are the tasks in your list:");

        // loop through list and print every task in a new line
        int len = taskList.getSize();
        ArrayList<Task> tasks = taskList.getTaskList();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = tasks.get(i - 1);
            System.out.println(i + ". " + curTask);

            String toAdd = "\n" + curTask.toString();
            responseToUser +=  toAdd;
        }
        System.out.println("---------------------------------------------");

        responseToUser += "\n";
        responseToUser += separator;
        return responseToUser;
    }

    /**
     * Print matching tasks contained in an ArrayList.
     *
     * @param matchingTask List of tasks to be printed.
     */
    private String printMatchingTask(TaskList matchingTask) {
        String responseToUser = separator + "\n" + "Here are the matching tasks in your list:";

        System.out.println("---------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        // loop through list and print every task in a new line
        int len = matchingTask.getSize();
        ArrayList<Task> tasks = matchingTask.getTaskList();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = tasks.get(i - 1);
            System.out.println(i + ". " + curTask);

            String toAdd = "\n" + curTask.toString();
            responseToUser +=  toAdd;
        }
        System.out.println("---------------------------------------------");

        responseToUser += "\n";
        responseToUser += separator;
        return responseToUser;
    }

    public String showGoodbyeMessage(){
        System.out.println("---------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");

        return separator + "\n" + "Bye. Hope to see you again soon!" + "\n" + separator;
    }

    private String showCompleteMessage(Task task){
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("---------------------------------------------");

        return separator + "\n" + "Bye. Hope to see you again soon!" + "\n" + task.toString() + "\n" + separator;
    }

    private String showDeleteMessage(Task task, int listSize){
        System.out.println("---------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
        System.out.println("---------------------------------------------");

        return separator + "\n" + "Noted. I've removed this task:" + "\n" + task.toString() + "\n" + "Now you have " + listSize + " tasks in the list." + "\n" + separator;
    }

    private String showAddMessage(Task task, int listSize){
        System.out.println("---------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
        System.out.println("---------------------------------------------");
        return separator + "\n" + "Got it. I've added this task:" + "\n" + task.toString() + "\n" + "Now you have " + listSize + " tasks in the list." + "\n" + separator;
    }

    public String showLoadingError(){
        System.out.println("---------------------------------------------");
        System.out.println("OOPS!! Duke fails to load, please restart!");
        System.out.println("---------------------------------------------");

        return separator + "\n" + "OOPS!! Duke fails to load, please restart!" + "\n" + separator;
    }

    public String showErrorMessage(String message){
        System.out.println("---------------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------------");

        return separator + "\n" + message + "\n" + separator;
    }
}
