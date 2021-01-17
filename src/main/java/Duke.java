import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you? \n");
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String command = input.split(" ")[0];
        while(!command.equals("bye")){
            if(command.equals("list")) {
                taskManager.printList();
            } else if (command.equals("done")) {
                int taskId = Integer.valueOf(input.split(" ")[1]) - 1;
                taskManager.done(taskId);
            } else {
                String task = input.split(" ", 2)[1];
                taskManager.add(command, task);
            }
            input = sc.nextLine();
            command = input.split(" ")[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
