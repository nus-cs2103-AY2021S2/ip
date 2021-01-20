import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Duke starts up with an introduction
        System.out.println("Hey, I'm Duke!\n" + "How can I help you?");
        while (input.hasNext()) {
            String command = input.next();
            if (command.equals("bye")) {
                //If user inputs 'bye' command, Duke exits
                System.out.println("Aw. It was nice chatting with you! Don't forget me! :)");
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
