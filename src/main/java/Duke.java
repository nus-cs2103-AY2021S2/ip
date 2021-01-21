import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob :D\n" + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye! See you soon!");
                break;
            } else {
                System.out.println(userInput);
            }
        }
    }
}
