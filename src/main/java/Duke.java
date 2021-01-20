import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("   Hello There! I'm Duke, always here for you!");
        System.out.println("   How can I help you today?");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    System.out.println("___\n   list\n___");
                    input = sc.next();
                    break;
                default:
                    System.out.println("___\n   blah\n___");
                    input = sc.next();
            }
        }

        System.out.println("___\n Bye! Hope to see you soon! :) \n___");
    }
}
