package duke.util;

import java.util.Scanner;
import java.util.List;

import duke.exception.NoMeaningException;

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
    public String printGreetings() {
        return greetings;
    }

    /**
     * Print Bye at the end of the program.
     */
    public String getBye() {
        return bye;
    }

    /**
     * Print whenever command list is called.
     * @param i current ordering number in the TaskList
     *        task Task to print
     */
    public String getList(int i, Task task) {
        String number = String.valueOf(i+1);
        return number + "." + task;
    }

    /**
     * Print whenever done command is called successfully.
     * @param task Task done
     */
    public String getDoneSuccess(Task task) {
        return "Nice I have marked this task as done!\n" + task;
    }

    /**
     * Print whenever there is exception when done command is called.
     */
    public String getDoneFail() {
        return doneFail;
    }

    /**
     * Print whenever delete command is called successfully.
     * @param task Task to delete
     *        size The size of the TaskList after removal
     */
    public String getDeleteSuccess(Task task, int size) {
        return "Noted. I've removed this task: \n" + task + "\n" +
                "Now you have " + size + " tasks in the list.";
    }

    /**
     * Print whenever there is exception when delete command is called.
     */
    public String getDeleteFail() {
        return deleteFail;
    }

    /**
     * Print whenever find command is called successfully.
     * @param listFound List<Task> found that we would like to print
     */
    public String getFindSuccess(List<Task> listFound) {
        String result = "";
        for (int i=0; i < listFound.size(); i++) {
            result += getList(i, listFound.get(i));
        }
        return result;
    }

    /**
     * Print whenever there is exception when find command is called.
     */
    public String getFindFail(NoMeaningException e) {
        return e.getMessage();
    }

    /**
     * Print whenever there is exception when task typed command is called.
     * @param e NoMeaningException that arises whenever task command failed.
     */
    public String getTaskFail(Exception e) {
        return e.getMessage();
    }

    /**
     * Print at the end when task typed command is called.
     * @param task Task to print
     *        size The current size of TaskList
     */
    public String getTaskFinally(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\n" +
        "Now you have " + size + " tasks in the list.";
    }

    public String readCommand() {
        return this.scan.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
