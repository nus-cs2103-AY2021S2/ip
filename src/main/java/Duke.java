import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Link";
        String userInput;
        System.out.println("Hello! I'm " + name);
        System.out.println("How are you feeling today?");
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = sc.nextLine();
        }
        System.out.println("Bye. Talk to me anytime!");
    }
}
