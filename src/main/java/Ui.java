import java.util.Scanner;
import java.util.List;

public class Ui {
    private Scanner scan;
    private static String greetings = "Hi, I am Bebong, a self-centered bot. I can't do anything for you.";
    private static String bye = "Goodbye, hope to not see you again.";
    private static String doneFail = "☹ OOPS!!! The description of a done cannot be empty.";
    private static String deleteFail = "☹ OOPS!!! The description of a delete cannot be empty.";

    public Ui() {
        Scanner scan = new Scanner(System.in);
    }

    /**
     * Print Greetings at the beginning of the program.
     */
    public void printGreetings() {
        System.out.println(greetings);
    }

    /**
     * Print Bye at the end of the program.
     */
    public void printBye() {
        System.out.println(bye);
    }

    /**
     * Print whenever command list is called.
     * @param i current ordering number in the TaskList
     *        task Task to print
     */
    public void printList(int i, Task task) {
        System.out.print(i+1);
        System.out.print(".");
        System.out.println(task);
    }

    /**
     * Print whenever done command is called successfully.
     * @param task Task done
     */
    public void printDoneSuccess(Task task) {
        System.out.println("Nice I have marked this task as done!");
        System.out.println(task);
    }

    /**
     * Print whenever there is exception when done command is called.
     */
    public void printDoneFail() {
        System.out.println(doneFail);
    }

    /**
     * Print whenever delete command is called successfully.
     * @param task Task to delete
     *        size The size of the TaskList after removal
     */
    public void printDeleteSuccess(Task task, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Print whenever there is exception when delete command is called.
     */
    public void printDeleteFail() {
        System.out.println(deleteFail);
    }

    public void printFindSuccess(List<Task> listOfSearchedTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i=0; i < listOfSearchedTasks.size(); i++) {
            printList(i, listOfSearchedTasks.get(i));
        }
    }

    public void printFindFail(NoMeaningException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Print whenever there is exception when task typed command is called.
     * @param e NoMeaningException that arises whenever task command failed.
     */
    public void printTaskFail(NoMeaningException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Print at the end when task typed command is called.
     * @param task Task to print
     *        size The current size of TaskList
     */
    public void printTaskFinally(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
