import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> storageList = new ArrayList<>();
        int listCounter = 1;
        String name = "Link";
        String userInput;
        System.out.println("Hello! I'm " + name);
        System.out.println("How are you feeling today?");
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                while (storageList.size() != listCounter) {
                    System.out.println(listCounter + ". " + storageList.get(listCounter - 1));
                    listCounter++;
                }
                System.out.println(listCounter + ". " + storageList.get(listCounter - 1));
                userInput = sc.nextLine();
            } else {
                storageList.add(userInput);
                System.out.println("added: " + userInput);
                userInput = sc.nextLine();
            }
        }
        System.out.println("Bye. Talk to me anytime!");
    }
}
