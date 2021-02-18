package Level1_9;
import ip.src.main.java.Duke;
import ip.src.main.java.Task;

import java.util.Scanner;

public class Level_3 {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Duke bot = new Duke();
        //int counter = 1;

        while(!(input.equals("bye"))) {
            if (input.equals("list")) {
                bot.printList();
            }else{
                String command = input.split(" ")[0];
                if(command.equals("done")){
                    int id = Integer.valueOf(input.split(" ")[1]);
                    bot.markTaskAsDone(id);

                }else {
                    Task newTask = new Task(input);
                    bot.addToList(newTask);
                    //counter++;
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
