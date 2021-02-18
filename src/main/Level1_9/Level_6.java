package Level1_9;
import ip.src.main.java.*;

import java.util.Scanner;

public class Level_6 {
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Duke bot = new Duke();
        //int counter = 1;

        while (!(input.equals("bye"))) {
            if (input.equals("list")) {
                bot.printList();
            } else {
                String command = input.split(" ")[0];
                try {
                    if (command.equals("done")) {
                        int id = Integer.valueOf(input.split(" ")[1]);
                        bot.markTaskAsDone(id);

                    } else if (command.equals("todo")) {
                        try {
                            input = input.split(" ", 2)[1];
                            ToDo newTask = new ToDo(input);
                            bot.addToList(newTask);
                            //counter++;
                        } catch (Exception e) {
                            throw new DukeException(("OOPS!!! The description cannot be empty."));
                        }
                    } else if (command.equals("event")) {
                        input = input.split(" ", 2)[1];
                        String content = input.split("/at")[0];
                        String at = input.split("/at")[1];
                        Event newTask = new Event(content, at);
                        bot.addToList(newTask);
                    } else if (command.equals("deadline")) {
                        input = input.split(" ", 2)[1];
                        String content = input.split("/by")[0];
                        String by = input.split("/by")[1];
                        Deadline newTask = new Deadline(content, by);
                        bot.addToList(newTask);
                    }else if(command.equals("delete")){
                        int id = Integer.valueOf(input.split(" ")[1]);
                        bot.deleteTask(id);

                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-{");
                    }
                } catch (DukeException e1) {
                    System.out.println(e1);

                }
            }

            input = sc.nextLine();
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}



