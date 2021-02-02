import java.io.IOException;
import java.util.*;
import duke.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, I'm Bob");
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        List<Task> savedTaskList = storage.loadData();

        InputHandler io = new InputHandler(savedTaskList);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(">>> Bye. Hope to see you again soon!");
                break;
            } else {
                io = io.processInput(input);
                storage.writeData(io.getTaskList());
            }
        }
    }
}
