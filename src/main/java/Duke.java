import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        String input;
        String response;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi! Nice to meet you, I'm " + name);
        System.out.println("How may I help you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            input = sc.nextLine();

            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println("list");
                    input = sc.nextLine();
                } else if (input.equals("blah")) {
                    System.out.println("blah");
                    input = sc.nextLine();
                }
            }
            System.out.println("Goodbye! Have a nice day!");
        }
    }
}
