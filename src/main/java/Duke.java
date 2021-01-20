import java.util.*;

public class Duke {
    void chat() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!(input.equals("bye"))) {
            System.out.println(input);
            in = new Scanner(System.in);
            input = in.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}