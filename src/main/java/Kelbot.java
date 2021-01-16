import java.util.*;

public class Kelbot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
        List<String> toDoList = new ArrayList<>();
        String input = sc.nextLine();
        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                // List out all the to-dos
                for (int i = 1; i <= toDoList.size(); i++) {
                    System.out.println(i + ". " + toDoList.get(i - 1));
                }
            } else {
                // Add to-do to list
                toDoList.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye le Bye!");
    }
}
