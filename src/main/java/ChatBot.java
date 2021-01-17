import java.util.*;

public class ChatBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (!input.equals("bye") && !input.equals("list")) {
                taskList.add((input));
                System.out.println("added: " + input);
            } else if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i + ". " + taskList.get(i - 1));
                }
            }
        }
    }
}
