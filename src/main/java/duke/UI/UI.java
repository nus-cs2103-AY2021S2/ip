package duke.UI;

import duke.task.Task;

import java.util.Scanner;

public class UI {
    static final String lines = "----------------------------------------";
    private static Scanner sc = new Scanner(System.in);


    public static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi There! Greetings from \n" + logo);
        System.out.println(lines + "\n" + " Good day! I'm Duke" + "\n" + " How can I help you today? " + "\n" + lines);

    }

    public static void displayEndMessage() {
        System.out.println(lines + "\n" + " Bye. Hope to see you again!" + "\n" + lines);
    }

    public static String readCommand(){
        return sc.nextLine();
    }

    public void printTask(int count, Task task){
        System.out.println((count + 1) + "." + task.toString());
    }

    public void printListHeader(){
        System.out.println( "\nHere are the tasks in your list:");
    }

    public void displayLines(){
        System.out.println(lines);
    }

    public void displayAddedTaskMessage(Task t, int size) {
        System.out.println(lines + "\nGot it. I've added this task: \n\t" + t.toString() + "\n Now you have "
                + size + " tasks in your list \n" + lines);
    }

    public static void displayDeletedTaskMessage(Task t){
        System.out.println(lines + "\nNice! I've removed this task: \n" + t.toString() + "\n" + lines);
    }

    public void displayDoneTaskMessage(Task t){
        System.out.println(lines + "\nNice! I'll make this task as done: \n" + t.toString() + "\n" + lines);
    }

    public void showError(String e){
        System.out.println(e);
    }

}
