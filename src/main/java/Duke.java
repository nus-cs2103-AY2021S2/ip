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
                int count = 1;
                for(Task s : tasks) {
                    if(s != null) {
                        System.out.println("    " + count + ". " + s.toString());
                        count++;
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
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " +commands);
                tasks[taskCount] = new Task(taskCount+1, false, commands);
                taskCount++;
                System.out.println("    ____________________________________________________________");
            }
            System.out.print("Enter a command: ");
            commands = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");

    }
}


