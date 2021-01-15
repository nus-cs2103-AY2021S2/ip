import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //introduction
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        //start reading data from user
        Scanner sc = new Scanner(System.in);
        boolean flagger = true;

        while (flagger) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                flagger = false;
            } else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }
    }
}
