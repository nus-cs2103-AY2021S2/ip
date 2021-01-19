import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String[] commands = new String[100];
        int index = 0;
        boolean exit = false;
        while (!exit) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exit = true;
            } else if (cmd.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + commands[i]);
                }
            } else {
                System.out.println("added: " + cmd);
                commands[index] = cmd;
                index++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}