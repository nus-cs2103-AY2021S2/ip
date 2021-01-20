import java.util.*;
import duke.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);

        InputHandler io = new InputHandler();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(">>> Bye. Hope to see you again soon!");
                break;
            } else {
                io = io.processInput(input);
            }

        }
    }
}
