import java.util.Scanner;

public class Vergil {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command;

        // Chatbot replies are prefixed with V |,
        // while user commands are prefixed with >>>.
        System.out.println("V | Welcome! I'm Vergil!");
        System.out.println("V | How may I help you?");
        System.out.println();

        do {
            System.out.print(">>> ");
            command = scan.nextLine();
            System.out.print("V | ");

            switch (command) {
                case "bye":
                    System.out.println("Bye. See you soon!");
                    break;

                default:
                    System.out.println(command);
                    System.out.println();
            }

        } while (!command.equals("bye"));
    }
}
