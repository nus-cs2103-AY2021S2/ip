import java.util.Scanner;

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

        //Implementation of Echoing and Exiting
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else {
                System.out.printf(response, input);
            }
        }
    }
}

