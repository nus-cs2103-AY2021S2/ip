import java.util.Scanner;

public class Duke {
    private static void replyFormat(String reply) {
        String separator = "____________________________________________________________\n";
        System.out.println(separator + reply + "\n" + separator);
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        replyFormat(greeting);
    }

    private static void echo(String userInput) {
        replyFormat(userInput);
    }

    private static void bye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        replyFormat(byeMessage);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();
        String userInput = sc.next();

        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = sc.next();
        }

        bye();
    }
}
