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
        List<String> store = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        while(true) {
            String command = scan.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                for(int i =0; i < store.size(); i++) {
                    System.out.println((i + 1) + ". " + store.get(i));
                }
            } else {
                store.add(command);
                System.out.println("added: " + command);
            }
        }

    }

}