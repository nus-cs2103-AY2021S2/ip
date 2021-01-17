import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Main Class of Execution for IP
 */
public class Duke {
    public static void main(String[] args) {
        //Logo Display
        String logo = " ____            _      ____   ____\n" +
                "|    \\ |        / \\    |    \\ |    \\\n" +
                "|____/ |       /___\\   |____/ |____/\n" +
                "|    \\ |      /     \\  |  \\   |    \\\n" +
                "|____/ |____ /       \\ |   \\_ |____/\n";
        System.out.println("You are now in the presence of\n" + logo);

        //Import IO
        Scanner sc = new Scanner(System.in);

        //General template for Blarb's response
        String response = "%s\n"
                + "\n"
                + "> ";

        //Greeting and Exit Phrases
        String greet = "This is BLARB\n"
                + "You may speak";

        String exit = "Hasta la vida, baby.";

        System.out.printf(response, greet);

        //Implementation of Adding and Listing
        //Uses ArrayList to store actions
        List<String> list = new ArrayList<>(100);

        String add = "added: %s";

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                int i = 1;
                StringBuilder sb = new StringBuilder();
                for (String str : list) {
                   sb.append(String.format("\n%d. %s", i++, str));
                }
                System.out.printf(response, sb.substring(1));
            } else {
                list.add(input);
                System.out.printf(response, String.format(add, input));
            }
        }
    }
}

