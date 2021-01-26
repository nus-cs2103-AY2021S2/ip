package duke;

public class Ui {
    public Ui() {}

    public void showLoadingError() {
        System.out.println("Error occurred while loading");
    }

    public void showWelcomeMsg() {
        System.out.println("Good morning comrade, welcome to KGB.\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("_____________________________________________");
    }

    public void showError(String msg) {
        System.out.println("    " + msg);
    }

    public void printTask(Task task) {
        System.out.println(task.toString());
    }

    // printings related to LIST
    public void showListMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    // printings related to BYE
    public void showByeMsg() {
        System.out.println("Goodbye comrade. Hope to see you again soon!");
    }

    // printings related to DONE
    public void showDoneMsg() {
        System.out.println("Nice! I've marked this task as done:");
    }

    // printings related to tasks
    public void showTaskMsg() {
        System.out.println("Got it. I've added this task:");
    }

    public void showTaskCount(int i) {
        System.out.println("Now you have " + i+ " tasks in the list.");
    }

    // printings related to delete
    public void showDeleteMsg() {
        System.out.println("Noted. I've removed this task:");
    }


}
