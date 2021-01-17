import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<String> storage;
    private static void processCommand(String command) {
        if (command.equals("list")) {
            for (String str : storage) {
                System.out.println(str);
            }
        } else {
            System.out.println("I will remember: " + command);
            storage.add(command);
        }
    }

    public static void main(String[] args) {
        storage = new ArrayList<String>();
        Scanner sc =  new Scanner(System.in);
        System.out.println("Greetings. My name is I-01B, but you may call me CHEF. What can I assist you with?");
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("I hope I have been of assistance. Goodbye. C:");
                break;
            } else {
                processCommand(command);
            }
        }
    }
}
