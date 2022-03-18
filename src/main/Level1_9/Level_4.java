package Level1_9;
import ip.src.main.java.Deadline;
import ip.src.main.java.Duke;
import ip.src.main.java.Event;
import ip.src.main.java.ToDo;

import java.util.Scanner;

public class Level_4 {
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

                }else if(command.equals("todo")) {
                    input = input.split(" ",2)[1];
                    ToDo newTask = new ToDo(input);
                    bot.addToList(newTask);
                    //counter++;
                }else if(command.equals("event")){
                    input = input.split(" ",2)[1];
                    String content = input.split("/at")[0];
                    String at = input.split("/at")[1];
                    Event newTask = new Event(content,at);
                    bot.addToList(newTask);
                }else{
                    input = input.split(" ",2)[1];
                    String content = input.split("/by")[0];
                    String by= input.split("/by")[1];
                    Deadline newTask = new Deadline(content,by);
                    bot.addToList(newTask);
                }

            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
