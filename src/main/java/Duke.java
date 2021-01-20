import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String input;
        ArrayList<String> arrayList = new ArrayList<String>();

        System.out.println("--------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            int listNumber = 1;

            if (input.equals("bye")) {
                System.out.println("--------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");
            } else if (input.equals("list")){
                System.out.println("--------------------------");
                for (String s : arrayList) {
                    System.out.print(listNumber+". "+ s);
                    System.out.print("\n");
                    listNumber++;
                }
                System.out.println("--------------------------");
            } else {
                arrayList.add(input);
                System.out.println("added: "+ input);
                System.out.println("--------------------------");
            }
        }
    }
}
