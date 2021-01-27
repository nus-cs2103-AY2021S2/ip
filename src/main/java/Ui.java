import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            return sc.nextLine();
        }
        return "";
    }

    public void showLine() {
        System.out.println("---------------------------------------------------------------------");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showWelcome() {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?:");
    }

    public void showNumberOfItems(int numOfItems) {
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    public void showTaskDone(Task task){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showListItems(){
        System.out.println("Here are the tasks on your list:");
    }

    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.toString());
    }

    public void showBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showFoundListItems() {
        System.out.println("Here are the matching tasks in your list:");
    }
}