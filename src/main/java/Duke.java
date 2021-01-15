import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + userInput);
                System.out.println("\t____________________________________________________________");
            }
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
