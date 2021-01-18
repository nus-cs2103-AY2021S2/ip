import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcomeMsg = "Olly: Hey! Welcome to the chatbot. What can I do for you today?";
        System.out.println(welcomeMsg);

        String[] texts = new String[100];
        int textCounter = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Olly: Goodbye for now, we will meet again.");
                break;
            }

            if (input.equals("list")) {
                System.out.println("Olly: Here you go! Your list of items:");
                for (int i = 0; i < textCounter; i++) {
                    System.out.println(i+1 + ". " + texts[i]);
                }
            } else {
                texts[textCounter] = input;
                textCounter++;
                System.out.println("[Added to list] " + input);
            }
        }
    }
}
