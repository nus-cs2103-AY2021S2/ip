import java.util.Scanner;

public class Duke {
    private static final String BORDER = "---------------";

    public static void main(String[] args) {
        Duke.printWithBorders("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                Duke.printWithBorders("Bye. Hope to see you soon!");
                return;
            } else {
                Duke.printWithBorders(userInput);
            }
        }
    }

    public static void printWithBorders(String message) {
        System.out.println(Duke.BORDER);
        System.out.println(message);
        System.out.println(Duke.BORDER);
    }
}
