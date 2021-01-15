import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static boolean isActive = true;

    private static List<String> items = new ArrayList<>();

    /**
     * Add item to internal list
     * @param name Name of the item
     */
    private static void addItem(String name) {
        items.add(name);
        System.out.println("___________________________________________________________");
        System.out.printf("added: %s\n", name);
        System.out.println("___________________________________________________________\n");
    }

    /**
     * List down all items in the internal list
     */
    private static void listItems() {
        System.out.println("___________________________________________________________");
        for(int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, items.get(i));
        }
        System.out.println("___________________________________________________________");
    }

    public static void main(String[] args) {
        // Opening message
        System.out.println("___________________________________________________________");
        System.out.printf("Meow, I'm %s\nWhat can I do for you today?\n", CHATBOT_NAME);
        System.out.println("___________________________________________________________\n");

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String command = "";

        // Respond to user's commands but exit when user enters bye
        while(isActive) {
            command = scanner.nextLine();

            if(command.equals("list")) {
                listItems();
            } else if(command.equals("bye")) {
                isActive = false;
            } else {
                addItem(command);
            }
        }

        // Closing message
        System.out.println("___________________________________________________________");
        System.out.println("Meow. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }
}