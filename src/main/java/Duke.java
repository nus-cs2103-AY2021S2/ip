import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> ls = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partition = "\n/**********************************************************/\n";

        System.out.println(logo + "\nHello! I'm Duke.\n" + "What can I do for you?\n" + partition);
        command = sc.nextLine();

        while(!command.equals("bye")) {
            switch (command) {
                case "list" :
                    printTasks();
                    System.out.println(partition);
                    break;
                default :
                    addTask(command);
                    System.out.println(partition);
            }

            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n" + logo);
    }

    public static void addTask(String task) {
        ls.add(task);
        System.out.println("added: " + task);
    }

    public static void printTasks() {
        for (int i = 1; i <= ls.size(); i++) {
            System.out.println("" + i + ". " + ls.get(i - 1));
        }
    }
}
