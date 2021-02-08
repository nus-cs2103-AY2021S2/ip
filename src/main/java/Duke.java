/**
 * Doge Duke implements a virtual pet application that
 * returns different commands passed by owner (user).
 *
 * @author Chia Jia-Xi, Kymie
 * @version 1.0
 * @since 2021-01-31
 */

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
        String greet = "Woof! I'm Doge Duke\n"
                + "What do you want me to do?\n"
                + "Type your request in below!\n";
        String goodbye = "Bye! Hope I was a good dog, "
                + "see you again soon!";
        String terminate = "bye";


        System.out.println(spacer + greet + spacer);

        int ctr = 0;
        HashMap<Integer, Command> commands = new HashMap<Integer, Command>();
        String separator = " ";

        while (true) {
            String input = sc.nextLine();
            try {

                // Check for errors in errorHandling() method
                errorHandling(input);

                // To recognise terminating condition
                if (input.equals(terminate)) {
                    System.out.println(spacer + goodbye + spacer);
                    break;
                }

                // To recognise List user input
                if (input.equals("list")) {
                    System.out.println(spacer);
                    printList(commands);
                    System.out.println(spacer);

                // To recognise Done user input
                } else if (input.contains("done")) {
                    String[] doneCommand = input.split(separator);
                    int id = Integer.parseInt(doneCommand[1]);
                    Command command = commands.get(id);
                    commands.replace(id, command.markDone());

                    System.out.println(spacer + "Woof! "
                            + "I have completed these commands before: "
                            + "\n");
                    printList(commands);
                    System.out.println(spacer);

                } else {
                    ctr++;
                    Command command;

                    // To recognise ToDo user input
                    if (input.contains("todo")) {
                        command = new ToDo(input);
                        commands.put(ctr, command);
                        System.out.println(spacer
                                + "Mlem I've added a new command for you to do: \n"
                                + commands.get(ctr)
                                + "\n"
                                + "Now I can do a total of "
                                + ctr
                                + " commands!"
                                + spacer);

                    // To recognise Event user input
                    } else if (input.contains("event")) {
                        String[] inputTime = input.split(" /at ");
                        command = new Event(inputTime[0], inputTime[1]);
                        commands.put(ctr, command);
                        System.out.println(spacer
                                + "Much wow! I've added a new command with an Event: \n"
                                + commands.get(ctr)
                                + "\n"
                                + "Now I can do a total of "
                                + ctr
                                + " commands!"
                                + spacer);

                    // To recognise Deadline user input
                    } else if (input.contains("deadline")) {
                        String[] inputTime = input.split(" /by ");
                        command = new Deadline(inputTime[0], inputTime[1]);
                        commands.put(ctr, command);
                        System.out.println(spacer
                                + "Woofers! I've added a new command with a Ded-line: \n"
                                + commands.get(ctr)
                                + "\n"
                                + "Now I can do a total of "
                                + ctr
                                + " commands!"
                                + spacer);

                    } else { }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        sc.close();
    }

    /**
     * Displays Hashmap of all commands Duke is
     * trained to do
     *
     * @param hm HashMap that retains all commands
     */
    static void printList(HashMap<Integer, Command> hm) {
        for (Integer i : hm.keySet()) {
            String key = i.toString();
            Command value = hm.get(i);
            System.out.println(key + ". " + value);
        }
    }

    /**
     * Displays Hashmap of all commands Duke is
     * trained to do
     *
     * @param hm HashMap that retains all commands
     */
    static void errorHandling(String errorInput) throws DukeException {
        String[] inputArr = errorInput.split(" ");

        // Empty description
        if (errorInput.contains("todo") ||
                errorInput.contains("deadline") ||
                errorInput.contains("event") &&
                inputArr[1].isEmpty()) {

            throw new DukeException("Whoops :( Your command description cannot be empty. Try again!");

        // Invalid description: unknown
        } else {
            throw new DukeException("Whoops :( I'm sorry, I'm not sure what that means. "
                    + "Did you forget to add a command type?");
        }
    }
}
