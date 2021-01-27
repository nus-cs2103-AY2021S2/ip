package kelbot;

import java.io.Serializable;
import java.util.Scanner;

public class UI implements Serializable {
    
    /*
     * Initializes UI
     */
    
    public UI() {
        System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
    }
    
    /*
     * Allow user to key in input
     */
    
    public Parser takeInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return new Parser(input);
    }
    
    /*
     * Print out Goodbye message
     */
    
    public void sayGoodbye() {
        System.out.println("Bye le bye! See you next time!");
    }
    
    /*
     * Print out full list
     */
    
    public void printList(TaskList taskList) {
        System.out.println(taskList);
    }
    
    /*
     * Print out message to show that task has been done
     */
    
    public void printDone(Task task) {
        System.out.println("Well done! You have completed this task!");
        System.out.println(task);
    }
    
    /*
     * Print out message to show that task has been deleted
     */
    
    public void printDelete(Task task) {
        System.out.println("Noted! You have deleted this task!");
        System.out.println(task);
    }
    
    /*
     * Print out message to show that task has been added
     */
    
    public void printAdd(Task task, int taskSize) {
        System.out.println("Okay! I have added:");
        System.out.println(task);
        System.out.println("Now there are " + taskSize + " tasks on the list");
    }
}
