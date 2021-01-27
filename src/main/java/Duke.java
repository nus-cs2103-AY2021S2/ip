import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> storedText = new ArrayList<>();
        System.out.println("Hello! I'm Icebear");
        System.out.println("What can I do for you?");
        while (true) {
            String nextCommand = scan.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (nextCommand.equals("list")) {
                for (int i = 0; i < storedText.size(); i++) {
                    System.out.println(i + 1 + ". " + storedText.get(i));
                }
            } else {
                storedText.add(nextCommand);
                System.out.println("added: " + nextCommand);
            }
        }
    }
}