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
        String separator = " ";

        while (true) {
            String input = sc.nextLine();
            if (input.equals(terminate)) {
                System.out.println(spacer + goodbye + spacer);
                break;
            }

            if (input.equals("list")) {
                System.out.println(spacer);
                printList(commands);
                System.out.println(spacer);

            } else if (input.contains("done")) {
                //split input to get int
                //int
                String[] doneCommand = input.split(separator);
                String markedInput = "[X] " + input;
                int id = Integer.parseInt(doneCommand[1]);
                commands.replace(id, markedInput);

                System.out.println(spacer + "Woof! I have completed these commands before: " + "\n");
                printList(commands);
                System.out.println(spacer);

            } else {
                ctr++;
                String emptyInput = "[ ] " + input;
                commands.put(ctr, emptyInput);
                System.out.println(spacer + "added: " + input + spacer);
            }
        }
        sc.close();
    }

    static void printList(HashMap<Integer, String> hm) {
        for (Integer i : hm.keySet()) {
            String key = i.toString();
            String value = hm.get(i);
            System.out.println(key + ". " + value);
        }
    }


}
