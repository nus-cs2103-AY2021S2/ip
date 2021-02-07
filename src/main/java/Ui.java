import java.util.ArrayList;

public class Ui {

    public String greet(Storage storage) {
        String greeting = "Hello! I'm Kitty， こんにちは～ \u263a.\n" + "What can I do for you?\n"
                + storage.taskHistory();
        return greeting;
    }

    public String sayBye() {
        String exit = "Bye bye. Hope to see you again soon<3\n" + "Wish you all the best for CS2103T\n";
        Duke.canExit = true;
        return exit;
    }

    public String taskNotExist() {
        String errorMessage = "\u2639 OOPS!!! the task you are referring to does not seem to exist :-(";
        return errorMessage;
    }

    public String missingIndex() {
        String errorMessage = "\u2639 OOPS!!! you need to enter the index of the task :-(";
        return errorMessage;
    }

    public String noTask() {
        String message = "You have no task for now, yay!";
        return message;
    }

    public String listingTasksTitle(int numOfTasks) {
        return numOfTasks == 1 ? "Here is the task in your list:"
                : "Here are the tasks in your list:";
    }

    public String matchingTasksTitle(int numOfTask) {
        return (numOfTask == 1 ? "Here is the matching task in your list:"
                : "Here are the matching tasks in your list:");
    }

    public String doneConfirmMessage(String doneTask) {
        return "Nice! I've marked this task as done:\n"
                + doneTask;
    }

    public String addTaskConfirmMessage(String addedTask) {
        return "Got it. I've added this task: \n" + addedTask;
    }

    public String deleteTaskConfirmMessage(String deletedTask) {
        return "Noted. I've removed this task: \n" + deletedTask;
    }

    public String noMatchingTaskMessage() {
        return "You have no matching task in the list";
    }

    public String taskNumberReminder(int numOfTasks) {
        if (numOfTasks <= 1) {
            return "\nNow you have " + numOfTasks + " task in the list.";
        } else {
            return "\nNow you have " + numOfTasks + " tasks in the list.";
        }
    }

    public String printTasks(ArrayList<Task> tasks) {
        int i = 1;
        String mytasks = "\n";
        for (Task t : tasks) {
            mytasks += i + ". " + t.toString() + "\n";
            i++;
        }
        return mytasks;
    }

}
