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
            String command = sc.nextLine();
            String[] params = command.split(" ", 2);
            System.out.println("    ---------------------------------------");

            if (params[0].equals("bye")) {
                done = true;
                System.out.println("Bye bye!");
            } else if (params[0].equals("list")) {
                tm.listTasks();
            } else if (params[0].equals("done")) {
                tm.markTaskAsDone(Integer.parseInt(params[1]));
            } else if (params[0].equals("delete")) {
                tm.deleteTask(Integer.parseInt(params[1]));
            } else {
                tm.addTask(params);
            }
            System.out.println("    ---------------------------------------");
        }
        sc.close();
    }
}
