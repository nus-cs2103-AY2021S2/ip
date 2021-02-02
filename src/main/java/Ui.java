import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in = new Scanner(System.in);
    private final String separator = "---------------------------------------------";

    public Ui(){
        startDuke();
    }

    public static void startDuke() {
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
        String responseToUser = "Here are the tasks in your list:";

        // loop through list and print every task in a new line
        int len = taskList.getSize();
        ArrayList<Task> tasks = taskList.getTaskList();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = tasks.get(i - 1);
            System.out.println(i + ". " + curTask);

            String toAdd = "\n" + i + ". " + curTask.toString();
            responseToUser +=  toAdd;
        }

        return responseToUser;
    }

    /**
     * Print matching tasks contained in an ArrayList.
     *
     * @param matchingTask List of tasks to be printed.
     */
    private String printMatchingTask(TaskList matchingTask) {
        String responseToUser = separator + "\n" + "Here are the matching tasks in your list:";

        // loop through list and print every task in a new line
        int len = matchingTask.getSize();
        ArrayList<Task> tasks = matchingTask.getTaskList();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = tasks.get(i - 1);
            System.out.println(i + ". " + curTask);

            String toAdd = "\n" + i + ". " + curTask.toString();
            responseToUser +=  toAdd;
        }

        return responseToUser;
    }

    public String showGoodbyeMessage(){
        return "Bye. Hope to see you again soon!";
    }

    private String showCompleteMessage(Task task){
        return "Nice! I've marked this task as done:" + "\n" + task.toString();
    }

    private String showDeleteMessage(Task task, int listSize){
        return "Noted. I've removed this task:" + "\n" + task.toString() + "\n" + "Now you have " + listSize + " tasks in the list.";
    }

    private String showAddMessage(Task task, int listSize){
        return "Got it. I've added this task:" + "\n" + task.toString() + "\n" + "Now you have " + listSize + " tasks in the list.";
    }

    public String showLoadingError(){
        return "OOPS!! Duke fails to load, please restart!";
    }

    public String showErrorMessage(String message){
        return message;
    }
}
