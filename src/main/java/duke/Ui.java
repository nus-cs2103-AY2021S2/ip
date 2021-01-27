package duke;

import duke.tasks.Task;

import java.util.Scanner;
import java.util.List;

/**
 * UI class that is responsible for reading user input and printing
 */
public class Ui {
    
    /**
     * Constructor to initialize the UI Class
     */
    public Ui() {
    }

    /**
     * Returns A string which is the user input.
     *
     * @return user input.
     */
    public String read() {
        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();

        return action;
    }

    /**
     * Prints the divider line.
     */
    public void printDivider() {
        String DIVIDER = "    ___________________________________________";
        System.out.println(DIVIDER);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        String INTRODUCTION = "     Hello! I'm Duke\n" + "     What can I do for you?";
        System.out.println(INTRODUCTION);
    }

    /**
     * Prints the goodbye message.
     */
    public void printBye() {
        String FAREWELL = "     Bye. Hope to see you again soon!";
        System.out.println(FAREWELL);
    }

    /**
     * Prints out a task.
     *
     * @param task  A task.
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints out the string input
     *
     * @param str A string
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints the message when a task is added.
     */
    public void addPrint() {
        String PRINT_ADD_TASK = "     Got it. I've added this task: ";
        System.out.println(PRINT_ADD_TASK);
    }

    /**
     * Prints the error when attempting to load file message.
     */
    public void showLoadingError() {

        String UNABLE_TO_LOAD = "     Unable to load file. Creating new one";
        System.out.println(UNABLE_TO_LOAD);
    }

    /**
     * Prints out the number of tasks inside the TaskList
     *
     * @param list Tasklist
     */
    public void countTasks(TaskList list) {

        String COUNT_TASKS_MESSAGE = "     Now you have " + list.getList().size() + " tasks in the list.";
        System.out.println(COUNT_TASKS_MESSAGE);
    }

    /**
     * Prints the message when a task is removed.
     */
    public static void printRemoved() {

        String REMOVE_TASK_MESSAGE = "     Noted. I've removed this task: ";
        System.out.println(REMOVE_TASK_MESSAGE);
    }

    /**
     * Prints the message when a task is marked.
     */
    public static void printMarked() {

        String TASK_MARKED_MESSAGE =  "     Nice! I've marked this task as done:";
        System.out.println(TASK_MARKED_MESSAGE);
    }
    
    /**
     * @param list the Task list to be printed
     * Prints out the current task list
     */
    public void printList(List<Task> list) {
        
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("      " + (i + 1) + "." + task.toString().trim());
        }
        
    }
    
    /**
     * Prints out the number of tasks inside the TaskList on list command.
     *
     * @param list The tasklist.
     */
    public void printStored(TaskList list) {
        List<Task> taskList = list.getList();

        String TASKS_LIST_MESSAGE = "     Here are the tasks in your list:";
        System.out.println(TASKS_LIST_MESSAGE);

        this.printList(taskList);
    }
    
    /**
     * Prints out the matching message when user is finding for a keyword in the list
     */
    public void printMatching() {
        String PRINT_MATCHING_MESSAGE = "     Here are the matching tasks in your list: ";
        System.out.println(PRINT_MATCHING_MESSAGE);
    }
    
}
