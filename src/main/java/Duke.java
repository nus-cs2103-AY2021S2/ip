import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.print("User (Enter an input): ");
        String userInput = sc.nextLine();
        while (!"bye".equals(userInput)) {
            System.out.println("Duke: " + userInput);
            System.out.print("User (Enter an input): ");
            userInput = sc.nextLine();
        }
        System.out.println("Duke: Bye, hope to see you again! :)");
    }
}
