import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "   ___     ___    _  _     ___     ___     _     \n" +
                "  |   \\   /   \\  | \\| |   |_ _|   | __|   | |    \n" +
                "  | |) |  | - |  | .` |    | |    | _|    | |__  \n" +
                "  |___/   |_|_|  |_|\\_|   |___|   |___|   | ____| \n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        System.out.println("Welcome back Max, I'm a Dumb Assistant " +
                "Naively Intended (to) Ease Labour\n" + logo +
                "____________________________________________________________\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        //++++++++++++++++++++++++++ IGNORE THE CODE ABOVE +++++++++++++++++++++++++++++

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String temp = "";

                for (int i = 0; i < list.size(); i++) {
                    temp += String.format("%d. %s", i + 1, list.get(i));
                    if (i != list.size() - 1) {
                        temp += "\n";
                    }
                }
                System.out.println(
                    "____________________________________________________________\n" +
                    temp + "\n" +
                    "____________________________________________________________\n"
                );
            } else {
                list.add(input);
                System.out.println(
                    "____________________________________________________________\n" +
                    "added: " + input + "\n" +
                    "____________________________________________________________\n"
                );
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
