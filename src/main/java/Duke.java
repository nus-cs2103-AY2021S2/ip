import java.util.Scanner;
import java.util.HashMap;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n"
                + "░█░░░░░░░░▀▄░░░░░░▄░\n"
                + "█░░▀░░▀░░░░░▀▄▄░░█░█\n"
                + "█░▄░█▀░▄░░░░░░░▀▀░░█\n"
                + "█░░▀▀▀▀░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n"
                + "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n"
                + "░░▀░░░▀░░░░░▀░░░▀░░░\n";


        System.out.println("Greetings from\n" + logo);

        String spacer = "\n____________________________________________________________\n";
        String greet = "Woof! I'm Doge\n"
                + "What do you want me to do?\n"
                + "Type your request in below!\n";
        String goodbye = "Bye! Hope I was a good dog, see you again soon!";
        String terminate = "bye";


        System.out.println(spacer + greet + spacer);

        int ctr = 0;
        HashMap<Integer, String> commands = new HashMap<Integer, String>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals(terminate)) {
                System.out.println(spacer + goodbye + spacer);
                break;
            }

            if (input.equals("list")) {
                System.out.println(spacer);
                for (Integer i : commands.keySet()) {
                    String key = i.toString();
                    String value = commands.get(i);
                    System.out.println(key + ". " + value);
                }
                System.out.println(spacer);
            } else {
                ctr++;
                commands.put(ctr, input);
                System.out.println(spacer + "added: " + input + spacer);
            }
        }
        sc.close();
    }
}
