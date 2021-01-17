import java.util.*;

public class Duke {
    public static ArrayList<String> data = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);
        run(sc.nextLine());
        sc.close();
    }

    public static void run(String cmd) {
        System.out.println("    ____________________________________________________________");
        if(cmd.equals("bye")) {
            System.out.println("     Bye. Hope to see you again soon!" + "\n"
                    + "    ____________________________________________________________\n");
        } else if (cmd.equals("list")) {
            for(int i = 0; i < data.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + data.get(i));
            }
            System.out.println("    ____________________________________________________________\n");
            run(sc.nextLine());
        } else {
            System.out.println("     added: " + cmd + "\n"
                    + "    ____________________________________________________________\n");
            data.add(cmd);
            run(sc.nextLine());
        }
    }
}
