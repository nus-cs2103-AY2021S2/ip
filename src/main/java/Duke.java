import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String input;

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("--------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");
            } else {
                System.out.println("--------------------------");
                System.out.println(input);
                System.out.println("--------------------------");
            }
        }
    }
}
