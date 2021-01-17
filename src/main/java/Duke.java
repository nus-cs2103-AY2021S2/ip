import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Main Class of Execution for IP.
 */
public class Duke {
    /**
     * Main method for execution.
     * @param args Command Line Argument
     */
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

        //Greeting
        System.out.printf(response, "This is BLARB.\n You may speak.");

        //Implementation of Leaving, Adding, Listing and Marking.
        //Uses ArrayList to store actions
        List<Task> list = new ArrayList<>(100);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Hasta la vida, baby.");
                break;
            } else if (input.equals("list")) {
                int i = 1;
                StringBuilder sb = new StringBuilder();
                for (Task task : list) {
                    sb.append(String.format("\n%d. %s", i++, task.toString()));
                }
                System.out.printf(response, sb.substring(1));
            } else if (input.startsWith("done ")) {
                try {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    list.set(idx, list.get(idx).markAsDone());
                    String done = "I've marked this task as done:\n %s";
                    System.out.printf(response, String.format(done, list.get(idx)));
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    System.out.printf(response, "Sorry, there is no such task.");
                }
            } else {
                list.add(new Task(input));
                System.out.printf(response, String.format("Added: %s", input));
            }
        }
    }
}

