import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.print("Enter a command: ");
        String commands = in.nextLine();
        int taskCount = 0;
        while(!commands.equals("bye")) {
            if(commands.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for(Task s : tasks) {
                    if(s != null) {
                        System.out.println("    " + s.number + ". " + s.toString());
                    } else {
                        break;
                    }

                }
                System.out.println("    ____________________________________________________________");
            } else if(commands.split(" ")[0].equals("done")) {
                int n = Integer.parseInt(commands.split(" ")[1]);
                tasks[n-1].markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("    " + tasks[n-1].toString());
                System.out.println("    ____________________________________________________________");
            } else if(commands.split(" ")[0].equals("todo")) {
                int iend = commands.indexOf(" ");
                tasks[taskCount] = new ToDo(taskCount+1, commands.substring(iend+1));
                tasks[taskCount].addTask(taskCount+1);
                taskCount++;
            } else if(commands.split(" ")[0].equals("deadline")) {
                int iend1 = commands.indexOf(" ");
                int iend = commands.indexOf("/");
                String subString1= commands.substring(iend1+1 , iend);
                String subString2= commands.substring(iend+4);
                tasks[taskCount] = new Deadline(taskCount+1, subString1, subString2);
                tasks[taskCount].addTask(taskCount+1);
                taskCount++;
            } else if(commands.split(" ")[0].equals("event")) {
                int iend1 = commands.indexOf(" ");
                int iend = commands.indexOf("/");
                String subString1= commands.substring(iend1+1 , iend);
                String subString2= commands.substring(iend+4);
                tasks[taskCount] = new Event(taskCount+1, subString1, subString2);
                tasks[taskCount].addTask(taskCount+1);
                taskCount++;
            }
            System.out.print("Enter a command: ");
            commands = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");

    }
}


