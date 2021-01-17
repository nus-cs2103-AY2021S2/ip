import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    static void display(String str) {
        String[] strings = str.split("\n");

        System.out.println("    " + "___________________________________________________________________");
        for (String s : strings) {
            System.out.println("    " + s);
        }
        System.out.println("    " + "___________________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        display("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                display("Bye. Hope to see you again soon!");
                return;
            } else {
                display(command);
            }
        }
    }
}
