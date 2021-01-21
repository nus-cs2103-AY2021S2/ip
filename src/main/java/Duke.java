import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String h_rule = "────────────────────────────────────────────────────────────────────";
        System.out.println("Greetings! I am Duke! How may I assist you?\n" + h_rule);

        Scanner sc = new Scanner(System.in);

        String input;

        while (true) {
            input = sc.nextLine().trim();
            System.out.println(h_rule);
            if (input.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!\n");
                break;
            } else {
                System.out.println(input);
            }
            System.out.println(h_rule);
        }
        sc.close();
    }
}
