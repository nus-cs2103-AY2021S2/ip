import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static boolean isActive = true;

    public static void main(String[] args) {
        // Opening message
        System.out.println("___________________________________________________________");
        System.out.printf("Meow, I'm %s\nWhat can I do for you today?\n", CHATBOT_NAME);
        System.out.println("___________________________________________________________\n");

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String command = "";

        // Echo user's commands but exit when user enters bye
        while(isActive) {
            command = scanner.nextLine();

            if(command.equals("bye")) {
                isActive = false;
            } else {
                System.out.println("___________________________________________________________");
                System.out.println(command);
                System.out.println("___________________________________________________________\n");
            }
        }

        // Closing message
        System.out.println("___________________________________________________________");
        System.out.println("Meow. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }
}