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

        //General templates for Blarb's response
        String response = "%s\n"
                + "\n"
                + "> ";

        String addTask = "Affirmative. I've added this task: \n %s \n"
                + "Now you have %d tasks in the list.";

        //Greeting
        System.out.printf(response, "This is BLARB.\nYou may speak.");

        //Implementation of Leaving, Adding, Listing and Marking.
        //Commandized Task Initialization
        //Uses ArrayList to store actions
        List<Task> list = new ArrayList<>(100);
        while (sc.hasNextLine()) {
            //Reads input into two tokens, first part command, second part details.
            String input = sc.nextLine();
            String[] tokens = input.split(" ", 2);

            //Executes action depending on parsed command
            if (tokens[0].equalsIgnoreCase("bye") && tokens.length < 2) {
                System.out.println("Hasta la vida, baby.");
                break;
            } else if (tokens[0].equalsIgnoreCase("list") && tokens.length < 2) {
                if (list.size() < 1) {
                    System.out.printf(response, "You have nothing on your list.");
                } else {
                    int i = 1;
                    StringBuilder sb = new StringBuilder("Here are your tasks: \n");
                    for (Task task : list) {
                        sb.append(String.format("\n%d. %s", i++, task.toString()));
                    }
                    System.out.printf(response, sb.substring(1));
                }
            } else if (tokens[0].equalsIgnoreCase("done")) {
                try {
                    int idx = Integer.parseInt(tokens[1]) - 1;
                    list.get(idx).markAsDone();
                    String done = "I've marked this task as done:\n %s";
                    System.out.printf(response, String.format(done, list.get(idx)));
                } catch (IndexOutOfBoundsException ex) {
                    System.out.printf(response, "There is no such task.");
                } catch (NumberFormatException ex) {
                    System.out.printf(response,
                            String.format("I have detailed files on human anatomy, but not %s.",
                                    input));
                }
            } else if (tokens[0].equalsIgnoreCase("todo")) {
                Task task = new ToDo(tokens[1]);
                list.add(task);
                System.out.printf(response, String.format(addTask, task.toString(), list.size()));
            } else if (tokens[0].equalsIgnoreCase("deadline")) {
                String[] fragments = tokens[1].split(" /by ");
                Task task = new Deadline(fragments[0], fragments[1]);
                list.add(task);
                System.out.printf(response, String.format(addTask, task.toString(), list.size()));
            } else if (tokens[0].equalsIgnoreCase("event")) {
                String[] fragments = tokens[1].split(" /at ");
                Task task = new Event(fragments[0], fragments[1]);
                list.add(task);
                System.out.printf(response, String.format(addTask, task.toString(), list.size()));
            } else {
                System.out.printf(response,
                        String.format("I have detailed files on human anatomy, but not %s.",
                                input));
            }
        }
    }
}

