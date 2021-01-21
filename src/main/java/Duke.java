import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
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
                int count = 1;
                for(String s : tasks) {
                    if(s != null) {
                        System.out.println(count + ". " + s);
                        count++;
                    } else {
                        break;
                    }

                }
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " +commands);
                tasks[taskCount] = commands;
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
