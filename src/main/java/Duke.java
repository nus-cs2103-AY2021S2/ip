import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String h_rule = "────────────────────────────────────────────────────────────────────";
        System.out.println("Greetings! I am Duke! How may I assist you?\n" + h_rule);

        Scanner sc = new Scanner(System.in);

        String input;
        ArrayList<String> itemList = new ArrayList<String>();

        while (true) {
            input = sc.nextLine().trim();
            System.out.println(h_rule);
            if (input.equals("bye")) {
                System.out.println("Goodbye! Have a nice day!\n");
                break;
            } else {
                switch (input) {
                    case "list":
                        int i = 1;
                        for (String s : itemList) {
                            System.out.println(i + ". " + s);
                        }
                        i++;
                        break;
                    default:
                        itemList.add(input);
                        System.out.println("Added " + input);
                }
            }
            System.out.println(h_rule);
        }
        sc.close();
    }
}
