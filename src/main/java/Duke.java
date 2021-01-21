import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        
        Scanner scanner = new Scanner(System.in);     
        ArrayList<String> Tasks = new ArrayList<String>();

        user_active: while (true) {
            String usercommand = scanner.nextLine();
            String[] commands = usercommand.split(" ");

            switch (commands[0]) {
                case "bye":     // exit
                    byebye();
                    break user_active;

                case "list":    // regurgitate tasks
                    for (int t = 0; t < Tasks.size(); t ++) {
                        System.out.println((t + 1) +  ". " + Tasks.get(t));
                    }

                    System.out.println("");
                    break;

                default:    // appends to list
                    Tasks.add(usercommand);
                    System.out.println("added: " + usercommand + "\n");

            }
        }

        scanner.close();
    }

    public static void greet() {
        System.out.println("What can I do for you?\n");
    }

    public static void byebye() {
        System.out.println("ByeBye. Hope to see you again soon!");
    }

}
