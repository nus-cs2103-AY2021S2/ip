import java.util.*;
public class Duke {
    static final String seperatorLine = "-----------------------------";
    public static void main(String[] args) throws DukeException {
        String welcome = "Hi, I'm Duke and I'm gonna be your assistant. Enjoy!!!";
        TaskManager taskManager = new TaskManager();
        System.out.println(welcome);
        System.out.println();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String command = sc.nextLine();
                if (command.equals("bye")) {
                    System.out.println(seperatorLine);
                    System.out.println("Bye! Hope to see you again soon!");
                    System.out.println(seperatorLine);
                } else if (command.equals("list")) {
                    taskManager.listTask();
                } else if (command.startsWith("done")) {
                    taskManager.markDone(command.substring(5));
                } else {
                    taskManager.addTask(command);
                }
            } catch (DukeException err) {
                System.out.println(seperatorLine);
                System.out.println(err.getMessage());
                System.out.println(seperatorLine);
            }
        }
    }
}
