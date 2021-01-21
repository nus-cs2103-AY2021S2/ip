import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.print("Enter a command: ");
        String commands = in.next();
        while(!commands.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + commands);
            System.out.println("    ____________________________________________________________");
            System.out.print("Enter a command: ");
            commands = in.next();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");

    }
}
