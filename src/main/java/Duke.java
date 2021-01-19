import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String cmd = sc.next();
            if (cmd.equals("bye")) {
                exit = true;
            } else {
                System.out.println(cmd);
                cmd = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}