package kelbot;

import java.util.Scanner;

public class UI {
    
    /**
     * Initializes UI
     */
    
    public UI() {
        System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
    }
    
    /**
     * Allow user to key in input
     *
     * @return Parser that takes in the input and returns the information needed
     */
    
    public Parser takeInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return new Parser(input);
    }
    
    /**
     * Print out Goodbye message
     */
    
    public void sayGoodbye() {
        System.out.println("Bye le bye! See you next time!");
    }
    
    /**
     * Print out full list
     *
     * @param taskList The task list to be printed
     */
    
    public void printList(TaskList taskList) {
        System.out.println(taskList);
    }
    
    /**
     * Print out message to show that task has been done
     *
     * @param task The task that has been marked done
     */
    
    public void printDone(Task task) {
        System.out.println("Well done! You have completed this task!");
        System.out.println(task);
    }
    
    /**
     * Print out message to show that task has been deleted
     *
     * @param task The task that has been deleted
     */
    
    public void printDelete(Task task) {
        System.out.println("Noted! You have deleted this task!");
        System.out.println(task);
    }
    
    /**
     * Print out message to show that task has been added
     *
     * @param task The task that has been added
     * @param taskListSize The number of tasks in the task list
     */
    
    public void printAdd(Task task, int taskListSize) {
        System.out.println("Okay! I have added:");
        System.out.println(task);
        System.out.println("Now there are " + taskListSize + " tasks on the list");
    }
}
