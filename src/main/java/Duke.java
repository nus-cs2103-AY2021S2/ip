import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printList(inputList);
            } else {
                inputList.add(input);
                System.out.println("Added: " + input);
            }
        }
    }

    private static void printList(ArrayList<String> list) {
        for (int index = 0; index < list.size(); index++) {
            System.out.print((index + 1) + ". " + list.get(index) + "\n");
        }
    }
}
