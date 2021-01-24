import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
        System.out.println("");
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                String[] checkedInput = Parser.check(input);
                String command = checkedInput[0];
                if (command.equals("bye")) {
                    break;
                } else {
                    taskManager.manage(checkedInput);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
