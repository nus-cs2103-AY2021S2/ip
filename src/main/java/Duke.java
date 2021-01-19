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
        String input = sc.next();
        List<String> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {

                }
            }
            System.out.println(
                    "____________________________________________________________\n" +
                    input + "\n" +
                    "____________________________________________________________\n"
            );
            input = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
