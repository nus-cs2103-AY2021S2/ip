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
        String command = sc.nextLine();
        while(!command.equals("bye")){
            if(!command.equals("list")) {
                taskManager.add(command);
            } else {
                taskManager.printList();
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
