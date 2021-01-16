import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        List<Task> store = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        while(true) {
            String command = scan.nextLine();
            switch (command.toLowerCase()) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i =0; i < store.size(); i++) {
                        System.out.println((i + 1) + "." + store.get(i).display());
                    }
                    break;
                default:
                    String findDone = "";
                    if (command.length() > 4) {
                        findDone = command.substring(0, 4);
                    }
                    if (findDone.equalsIgnoreCase("done")) {
                            if (command.charAt(4) == ' ') {
                                int number = Integer.parseInt(command.substring(5));
                                Task toMark = store.get(number - 1);
                                String response = toMark.markAsDone();
                                System.out.println(response);
                            }
                    } else {
                        Task newTask = new Task(command);
                        store.add(newTask);
                        System.out.println("added: " + command);
                    }
            }
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
        }
    }
}
