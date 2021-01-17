import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____            _      ____   ____\n" +
                "|    \\ |        / \\    |    \\ |    \\\n" +
                "|____/ |       /___\\   |____/ |____/\n" +
                "|    \\ |      /     \\  |  \\   |    \\\n" +
                "|____/ |____ /       \\ |   \\_ |____/\n";
        System.out.println("You are now in the presence of\n" + logo);

        Scanner sc = new Scanner(System.in);

        String response = "%s\n"
                + "\n"
                + "> ";

        String intro = "This is BLARB\n"
                + "You may speak";

        String esc = "Hasta la vida, baby.";

        System.out.printf(response, intro);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(esc);
                break;
            } else {
                System.out.printf(response, input);
            }
        }
    }
}

