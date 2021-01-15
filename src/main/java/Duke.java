import java.util.Scanner;

public class Duke {
    /**
     *  Greeter for Duke
    */
    public static void greet() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    ---------------------------------------");
        System.out.println(logo);
        System.out.println("    ---------------------------------------");
        System.out.println("    Hello! This is Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ---------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        greet();

        boolean done = false;
        while (!done) {
            String command = sc.next();
            System.out.println("    ---------------------------------------");
            if (command.equals("bye")) {
                done = true;
                System.out.println("Bye bye!");
            } else if (command.equals("list")) {
                tm.listTasks();
            } else if (command.equals("done")) {
                Integer index = Integer.parseInt(sc.next());
                tm.markTaskAsDone(index);
            } else {
                tm.addTask(command);
            }
            System.out.println("    ---------------------------------------");
        }
    }
}
