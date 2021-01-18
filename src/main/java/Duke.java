import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMsg = "Olly: Hey! Welcome to the chatbot. What can I do for you today?";
        System.out.println(welcomeMsg);

        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Olly: Goodbye for now, we will meet again.");
                break;
            }
            System.out.print("You: ");
            System.out.println(input);
        }
    }
}
