package kelbot;

import java.util.Scanner;

public class UI {
    
    public UI() {
        System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
    }
    
    public Parser takeInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return new Parser(input);
    }
    
    public void sayGoodbye() {
        System.out.println("Bye le bye! See you next time!");
    }
    
    public void printList(TaskList taskList) {
        System.out.println(taskList);
    }
    
    public void printDone(Task task) {
        System.out.println("Well done! You have completed this task!");
        System.out.println(task);
    }
    
    public void printDelete(Task task) {
        System.out.println("Noted! You have deleted this task!");
        System.out.println(task);
    }
    
    public void printAdd(Task task, int taskSize) {
        System.out.println("Okay! I have added:");
        System.out.println(task);
        System.out.println("Now there are " + taskSize + " tasks on the list");
    }
}
