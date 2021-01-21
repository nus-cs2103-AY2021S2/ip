import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! What can I do for you:>");

        String input = sc.next();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.next();
        }

        System.out.println("Bye. See you again!");
    }
}
