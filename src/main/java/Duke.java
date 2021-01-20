import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> currList = new ArrayList<>();

        //Duke starts up with an introduction
        System.out.println("Hey, I'm Duke!\n" + "How can I help you?");
        while (input.hasNextLine()) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                //If user inputs 'bye' command, Duke exits
                System.out.println("Aw. It was nice chatting with you! Don't forget me! :)");
                break;
            } else if (command.equals("list")) {
                //List out each element of currList
                for(int i = 0; i < currList.size(); i++) {
                    System.out.println((i + 1) + ". " + currList.get(i));
                }
            } else {
                //Add user's command to currList
                currList.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
