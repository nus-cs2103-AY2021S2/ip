import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Duke \n What can I do for you?";
        System.out.println(greet);
        String input = sc.nextLine();
        while (!(input.equals("bye")) && !(input.equals("Bye"))) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
