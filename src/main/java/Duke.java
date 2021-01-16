import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        performFirstTask();
    }

    public static void performFirstTask() {
        String line = "---------------------------";
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(line);
        System.out.println(greet);
        System.out.println(line);
        String cmd = "";
        Scanner input = new Scanner(System.in);
        while(!cmd.equals("bye")) {
            cmd = input.next();
            System.out.println(line);
            System.out.println(cmd);
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
